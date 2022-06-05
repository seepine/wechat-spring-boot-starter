package com.seepine.wechat.constant;

/**
 * @author Seepine
 */
public interface ApiConstant {
  /**
   * 微信小程序jsCode转openId的url
   *
   * <p>https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
   */
  String MINI_APP_AUTHORIZATION_CODE_URL =
      "https://api.weixin.qq.com/sns/jscode2session"
          + "?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

  /** 微信小程序获取access的url */
  String ACCESS_URL =
      "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

  /**
   * 微信小程序获取无限制小程序码
   *
   * <p>https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/qr-code/wxacode.getUnlimited.html#method-http
   */
  String MINI_APP_GET_UN_LIMITED_CODE =
      "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=%s";

  /**
   * 消息订阅
   *
   * <p>https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.send.html
   */
  String SUBSCRIBE_SEND_URL =
      "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=%s";

  /**
   * 获取小程序 scheme Link
   *
   * <p>https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/url-scheme/urlscheme.generate.html
   */
  String SCHEME_LINK_GENERATE = "https://api.weixin.qq.com/wxa/generatescheme?access_token=%s";

  /**
   * 获取小程序 URL Link
   *
   * <p>https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/url-link/urllink.generate.html
   */
  String URL_LINK_GENERATE = "https://api.weixin.qq.com/wxa/generate_urllink?access_token=%s";

  /**
   * code换取用户手机号。 每个code只能使用一次，code的有效期为5min
   *
   * <p>https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/phonenumber/phonenumber.getPhoneNumber.html
   */
  String GET_USER_PHONE_NUMBER =
      "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=%s";
}
