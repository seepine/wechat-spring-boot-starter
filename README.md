## wechat-spring-boot-starter

集成微信小程序接口

- jsCode转openId
- accessToken获取
- 发送消息订阅
- 生成小程序码
- 通过code获取手机号
- 生成小程序URL link
- 生成小程序Scheme link

## spring boot starter依赖

- Latest
  Version: [![Maven Central](https://img.shields.io/maven-central/v/com.seepine/wechat-spring-boot-starter.svg)](https://search.maven.org/search?q=g:com.seepine%20a:wechat-spring-boot-starter)
- Maven:

```xml

<dependency>
  <groupId>com.seepine</groupId>
  <artifactId>wechat-spring-boot-starter</artifactId>
  <version>${latest.version}</version>
</dependency>
```

## 使用方法

### 1.配置文件

```
we-chat:
  app-id: ${your key}
  app-secret: ${your secret}
```

### 2.代码使用

注入template

```
@Autowire
private WeChatTemplate weChatTemplate;
```

方法使用

```java
class Test {
  public static void main(String[] args) {
    String openId = weChatTemplate.getOpenId(jsCode);
    String accessToken = weChatTemplate.getAccess();
    // 1.1.0支持
    String phone = weChatTemplate.getPhone(jsCode, encryptedData, iv);
    // 1.1.1支持
    byte[] qrByte = weChatTemplate.getUnLimitedCode(accessToken, scene, page);
    // 1.1.3支持
    weChatTemplate.sendSubscribe(body);

    // 1.3.0支持
    // 详见https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/getPhoneNumber.html
    WxPhoneBody phoneBody = weChatTemplate.getPhone(accessToken, code);
    String phone = weChatTemplate.getPurePhone(accessToken, code);

  }
}
```
