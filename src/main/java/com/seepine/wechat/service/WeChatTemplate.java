package com.seepine.wechat.service;

import com.seepine.http.HttpClientPool;
import com.seepine.http.Request;
import com.seepine.http.Response;
import com.seepine.json.Json;
import com.seepine.json.JsonObject;
import com.seepine.wechat.WeChatProperties;
import com.seepine.wechat.constant.ApiConstant;
import com.seepine.wechat.constant.ErrorConstant;
import com.seepine.wechat.constant.KeyConstant;
import com.seepine.wechat.entity.*;
import com.seepine.wechat.exception.WeChatException;
import com.seepine.wechat.util.WxUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @author Seepine
 */
@Slf4j
@RequiredArgsConstructor
public class WeChatTemplate implements InitializingBean {

  private final WeChatProperties weChatProperties;

  @Override
  public void afterPropertiesSet() {
    if (HttpClientPool.get("we_chat") == null) {
      HttpClientPool.put("we_chat");
    }
  }

  /**
   * jsCode转openId
   *
   * @param jsCode 小程序wx.login获得的jsCode
   * @return openId
   * @throws WeChatException exception
   */
  public WxSession jsCode2Session(String jsCode) throws WeChatException {
    Response response =
        Request.get(
                String.format(
                    ApiConstant.MINI_APP_AUTHORIZATION_CODE_URL,
                    weChatProperties.getAppId(),
                    weChatProperties.getAppSecret(),
                    jsCode))
            .httpClientName("we_chat")
            .execute();
    JsonObject res = WxUtil.res(response);
    String openId = res.getStr(KeyConstant.OPENID_KEY);
    String sessionKey = res.getStr(KeyConstant.SESSION_KEY);
    Assert.hasText(openId, ErrorConstant.OPEN_ID_ERROR_MESSAGE);
    Assert.hasText(sessionKey, ErrorConstant.SESSION_KEY_ERROR_MESSAGE);
    return WxSession.builder().openId(openId).sessionKey(sessionKey).build();
  }

  /**
   * jsCode转openId
   *
   * @param jsCode 小程序wx.login获得的jsCode
   * @return openId
   * @throws WeChatException exception
   */
  public String getOpenId(String jsCode) throws WeChatException {
    WxSession wxSession = jsCode2Session(jsCode);
    return wxSession.getOpenId();
  }

  /**
   * 获取accessToken
   *
   * @return accessToken
   * @throws WeChatException Exception
   */
  public String getAccess() throws WeChatException {
    Response response =
        Request.get(
                String.format(
                    ApiConstant.ACCESS_URL,
                    weChatProperties.getAppId(),
                    weChatProperties.getAppSecret()))
            .httpClientName("we_chat")
            .execute();
    JsonObject res = WxUtil.res(response);
    String access = res.getStr(KeyConstant.ACCESS_TOKEN_KEY);
    Assert.hasText(access, ErrorConstant.ACCESS_TOKEN_ERROR_MESSAGE);
    return access;
  }

  /**
   * 通过code获取没有区号的手机号
   *
   * @param code 手机号获取凭证
   * @return 没有区号的手机号
   * @throws WeChatException exception
   */
  public String getPurePhone(String accessToken, String code) throws WeChatException {
    WxPhoneBody res = getPhone(accessToken, code);
    return res.getPurePhoneNumber();
  }
  /**
   * 通过code获取手机号
   *
   * @param code 手机号获取凭证
   * @return 手机号
   * @throws WeChatException exception
   */
  public WxPhoneBody getPhone(String accessToken, String code) throws WeChatException {
    Response response =
        Request.post(String.format(ApiConstant.GET_USER_PHONE_NUMBER, accessToken))
            .httpClientName("we_chat")
            .body("{\"code\":\"" + code + "\"}")
            .execute();
    JsonObject res = WxUtil.res(response);
    JsonObject jsonObject = res.getObj("phone_info");
    String phoneNumber = jsonObject.getStr("phoneNumber");
    String purePhoneNumber = jsonObject.getStr("purePhoneNumber");
    Integer countryCode = jsonObject.getInt("countryCode");
    Assert.hasText(purePhoneNumber, "获取不到purePhoneNumber");
    return WxPhoneBody.builder()
        .phoneNumber(phoneNumber)
        .purePhoneNumber(purePhoneNumber)
        .countryCode(countryCode)
        .build();
  }

  /**
   * 生成小程序码
   *
   * @param accessToken accessToken
   * @param scene eg:a=1&b=2
   * @param page 页面
   * @return 图片数据
   * @throws WeChatException exception
   */
  public byte[] getUnlimited(String accessToken, String scene, String page) throws WeChatException {
    return getUnlimited(accessToken, WxUnlimitedBody.builder().scene(scene).page(page).build());
  }

  /**
   * 生成小程序码
   *
   * @param accessToken accessToken
   * @param body WxUnlimitedBody
   * @return 图片数据
   * @throws WeChatException exception
   */
  public byte[] getUnlimited(String accessToken, WxUnlimitedBody body) throws WeChatException {
    Response response =
        Request.post(String.format(ApiConstant.MINI_APP_GET_UN_LIMITED_CODE, accessToken))
            .httpClientName("we_chat")
            .body(Json.toJson(body))
            .execute();
    if (!response.isOk()) {
      throw new WeChatException("请求失败");
    }
    if (response.contentType().toString().contains("json")) {
      WxUtil.resWithNull(response);
    }
    return response.bodyBytes();
  }

  /**
   * 发送消息订阅
   *
   * @param body body
   * @throws WeChatException e
   */
  public void sendSubscribe(String accessToken, WxSubscribeBody body) throws WeChatException {
    Response response =
        Request.post(String.format(ApiConstant.SUBSCRIBE_SEND_URL, accessToken))
            .httpClientName("we_chat")
            .body(Json.toJson(body))
            .execute();
    WxUtil.resWithNull(response);
  }

  /**
   * 获取小程序 scheme Link
   *
   * @param body body
   * @throws WeChatException e
   */
  public String generateSchemeLink(String accessToken, WxSchemeLinkBody body)
      throws WeChatException {
    Response response =
        Request.post(String.format(ApiConstant.SCHEME_LINK_GENERATE, accessToken))
            .httpClientName("we_chat")
            .body(Json.toJson(body))
            .execute();
    JsonObject res = WxUtil.res(response);
    String urlLink = res.getStr("openlink");
    Assert.hasText(urlLink, "生成urlLink失败");
    return urlLink;
  }
  /**
   * 获取小程序 URL Link
   *
   * @param body body
   * @throws WeChatException e
   */
  public String generateUrlLink(String accessToken, WxUrlLinkBody body) throws WeChatException {
    Response response =
        Request.post(String.format(ApiConstant.URL_LINK_GENERATE, accessToken))
            .httpClientName("we_chat")
            .body(Json.toJson(body))
            .execute();
    JsonObject res = WxUtil.res(response);
    String urlLink = res.getStr("url_link");
    Assert.hasText(urlLink, "生成urlLink失败");
    return urlLink;
  }
}
