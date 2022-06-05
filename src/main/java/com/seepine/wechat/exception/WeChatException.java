package com.seepine.wechat.exception;

/** @author wraptor */
public class WeChatException extends Exception {
  String message;
  String errMsg;
  Integer errCode;

  public WeChatException(String message) {
    this.message = message;
  }

  public WeChatException(Integer errCode, String errMsg, String message) {
    this.errCode = errCode;
    this.errMsg = errMsg;
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public Integer getErrCode() {
    return this.errCode;
  }

  public String getErrMsg() {
    return this.errMsg;
  }
}
