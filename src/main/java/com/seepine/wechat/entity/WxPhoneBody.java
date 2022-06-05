package com.seepine.wechat.entity;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author seepine
 */
@Getter
@Builder
public class WxPhoneBody implements Serializable {
  static final long serialVersionUID = 0L;
  /** 用户绑定的手机号（国外手机号会有区号） */
  private String phoneNumber;
  /** 没有区号的手机号 */
  private String purePhoneNumber;
  /** string */
  private Integer countryCode;
}
