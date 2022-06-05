package com.seepine.wechat.util;

import com.seepine.http.Response;
import com.seepine.json.Json;
import com.seepine.json.JsonObject;
import com.seepine.wechat.exception.WeChatException;

public class WxUtil {
  private static final String ERR_CODE = "errcode";
  private static final String ERR_MSG = "errmsg";

  public static JsonObject res(Response response) throws WeChatException {
    if (!response.isOk()) {
      throw new WeChatException("请求失败");
    }
    JsonObject res = Json.parseObj(response.bodyStr());
    if (res.has(ERR_CODE)) {
      Integer errCode = res.getInt(ERR_CODE);
      if (errCode != 0) {
        throw new WeChatException(errCode, res.get(ERR_MSG).asText(), "请求失败");
      }
      throw new WeChatException("请求失败");
    }
    return res;
  }

  public static void resWithNull(Response response) throws WeChatException {
    res(response);
  }
}
