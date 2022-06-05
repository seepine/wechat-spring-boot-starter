package com.seepine.wechat.entity;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/** @author seepine */
@Getter
@Builder
public class WxSession implements Serializable {
  static final long serialVersionUID = 0L;

  private String openId;
  private String sessionKey;
}
