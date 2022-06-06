package com.seepine.wechat.entity;

import com.seepine.wechat.enums.EnvVersion;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class WxUnlimitedBody implements Serializable {
  static final long serialVersionUID = 0L;

  /** 必填,最大32个可见字符,eg:a=1 */
  String scene;
  /** 页面 page，例如 pages/index/index，根路径前不要填加 /，不能携带参数（参数请放在scene字段里），如果不填写这个字段，默认跳主页面 */
  String page;
  /**
   * 检查page 是否存在，为 true 时 page 必须是已经发布的小程序存在的页面（否则报错）；为 false 时允许小程序未发布或者 page 不存在， 但page
   * 有数量上限（60000个）请勿滥用
   */
  Boolean check_path;
  /** 要打开的小程序版本。正式版为 "release"，体验版为 "trial"，开发版为 "develop" */
  EnvVersion env_version;
  /** 二维码的宽度，单位 px，默认430，最小 280px，最大 1280px */
  Integer width;
  /** 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调，默认 false */
  Boolean auto_color;
  /**
   * auto_color 为 false 时生效，默认{"r":0,"g":0,"b":0}，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"}
   * 十进制表示
   */
  Color line_color;
  /** 是否需要透明底色，为 true 时，生成透明底色的小程序，默认false */
  Boolean is_hyaline;
}
