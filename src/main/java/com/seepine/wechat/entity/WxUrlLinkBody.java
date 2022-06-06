package com.seepine.wechat.entity;

import com.seepine.wechat.enums.EnvVersion;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class WxUrlLinkBody implements Serializable {
  static final long serialVersionUID = 0L;
  /** 通过 URL Link 进入的小程序页面路径,必须是已经发布的小程序存在的页面，不可携带 query 。path 为空时会跳转小程序主页 */
  String path;

  /** 通过 URL Link 进入小程序时的query,最大1024个字符,只支持数字,大小写英文以及部分特殊字符 */
  String query;
  /**
   * 要打开的小程序版本。默认未正式版 "release"，体验版为"trial"，开发版为"develop"，仅在微信外打开时生效。体验版和开发版仅在iOS上支持，Android将在近期支持。
   */
  EnvVersion env_version;
  /**
   * 默认false，生成的 URL Link 类型，到期失效：true，永久有效：false。注意，永久有效 Link 和有效时间超过180天的到期失效 Link 的总数上限为10万个，详见获取
   * URL Link，生成 Link 前请仔细确认。
   */
  Boolean is_expire;
  /** 默认0，小程序 URL Link 失效类型，失效时间：0，失效间隔天数：1 */
  Integer expire_type;
  /** 到期失效的 URL Link 的失效时间，为 Unix 时间戳。生成的到期失效 URL Link 在该时间前有效。最长有效期为1年。expire_type 为 0 必填 */
  Integer expire_time;
  /** 到期失效的URL Link的失效间隔天数。生成的到期失效URL Link在该间隔时间到达前有效。最长间隔天数为365天。expire_type 为 1 必填 */
  Integer expire_interval;
  /** 云开发静态网站自定义 H5 配置参数，可配置中转的云开发 H5 页面。不填默认用官方 H5 页面 */
  CloudBase cloud_base;

  @Getter
  @Builder
  public static class CloudBase {
    /** 必填，云开发环境 */
    String env;
    /** 静态网站自定义域名，不填则使用默认域名 */
    String domain;
    /** 默认/，云开发静态网站 H5 页面路径，不可携带 query */
    String path;
    /** 云开发静态网站 H5 页面 query 参数，最大 1024 个字符，只支持数字，大小写英文以及部分特殊字符：`!#$&'()*+,/:;=?@-._~%`` */
    String query;
    /** 第三方批量代云开发时必填，表示创建该 env 的 appid （小程序/第三方平台） */
    String resource_appid;
  }
}
