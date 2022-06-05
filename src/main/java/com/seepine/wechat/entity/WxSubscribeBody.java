package com.seepine.wechat.entity;

import com.seepine.wechat.enums.MiniProgramState;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/** @author seepine */
@Getter
public class WxSubscribeBody implements Serializable {
  static final long serialVersionUID = 0L;

  /** 必填，接收者（用户）的 openid */
  String touser;
  /** 必填，所需下发的订阅模板id */
  String template_id;
  /** 必填，模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } } */
  Map<String, Object> data = new HashMap<>();
  /** 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。 */
  String page;
  /** 跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版 */
  MiniProgramState miniprogram_state;
  /** 进入小程序查看”的语言类型，支持zh_CN(简体中文)、en_US(英文)、zh_HK(繁体中文)、zh_TW(繁体中文)，默认为zh_CN */
  String lang;

  private WxSubscribeBody() {}

  public static WxSubscribeBody build(String openId, String templateId) {
    WxSubscribeBody body = new WxSubscribeBody();
    body.touser = openId;
    body.template_id = templateId;
    return body;
  }

  public WxSubscribeBody page(String page) {
    this.page = page;
    return this;
  }

  public WxSubscribeBody miniProgramState(MiniProgramState miniProgramState) {
    this.miniprogram_state = miniProgramState;
    return this;
  }

  public WxSubscribeBody lang(String lang) {
    this.lang = lang;
    return this;
  }

  public WxSubscribeBody addData(String key, String value) {
    Map<String, String> map = new HashMap<>();
    map.put("value", value);
    this.data.put(key, map);
    return this;
  }
}
