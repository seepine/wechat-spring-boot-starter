package com.seepine.wechat;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/** @author Seepine */
@Data
@ConfigurationProperties(prefix = "we-chat")
public class WeChatProperties {
  /** 微信小程序appId */
  private String appId;
  /** 微信小程序secret */
  private String appSecret;
}
