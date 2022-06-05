package com.seepine.wechat.entity;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class Color implements Serializable {
  static final long serialVersionUID = 0L;

  int r;
  int g;
  int b;
}
