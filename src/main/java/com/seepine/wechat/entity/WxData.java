package com.seepine.wechat.entity;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class WxData {
  public Map<String, Object> data = new HashMap<>();

  public static WxData build() {
    return new WxData();
  }

  public WxData add(String key, Object value) {
    data.put(key, value);
    return this;
  }
}
