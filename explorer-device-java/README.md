# 腾讯物联网开发平台Java SDK
腾讯物联网开发平台 Java SDK 配合平台对设备数据模板化的定义，实现和云端基于数据模板协议的数据交互框架，开发者基于IoT_Explorer Java SDK数据模板框架，快速实现设备和平台、设备和应用之间的数据交互。

## 控制台创建设备

设备接入SDK前需要在控制台中创建项目产品设备，获取产品ID、设备名称、设备证书（证书认证）、设备私钥（证书认证）、设备密钥（密钥认证），将设备与云端认证连接时需要用到。请参考官网 [用户指南-项目管理](https://cloud.tencent.com/document/product/1081/40290)、 [用户指南-产品定义](https://cloud.tencent.com/document/product/1081/34739)、 [用户指南-设备调试](https://cloud.tencent.com/document/product/1081/34741)。

## 工程配置 ##

**引用方式**
如果您想通过jar引用方式进行项目开发，可在module目录下的build.gradle中添加依赖，如下依赖：
```
dependencies {
    ...
    implementation("com.tencent.iot.explorer:explorer-device-java:1.0.0")
}
```

**Java Sdk源码**
如果您想通过代码集成方式进行项目开发，可访问[Github](https://github.com/tencentyun/iot-device-java/tree/master/explorer-device-java)下载Java Sdk源码。

## 设备认证说明

IoT Explorer物联网开发平台支持设备以密钥认证和证书认证，两种认证方式接入。

#### 密钥认证接入

示例中编辑 [IoTDataTemplate.java](https://github.com/tencentyun/iot-device-java/blob/master/explorer-device-java/src/main/java/com/tencent/iot/explorer/device/java/test/IoTDataTemplate.java) 文件中的参数配置信息
```
{
private static String mProductID = "";
private static String mDevName = "";
private static String mDevPSK  = ""; //若使用证书验证，设为null
}
```
如果控制台创建设备使用的是密钥认证方式，需要在 IoTDataTemplate.java 填写 mProductID（产品ID）、mDevName（设备名称）、mDevPSK（设备密钥），示例中使用的是密钥认证。

#### 证书认证接入

将证书放到resources文件夹中，将psk设置null，然后将证书名称填写入指定区域

如果控制台创建设备使用的是证书认证方式，除了需要在 IoTDataTemplate.java 填写 mProductID（产品ID）、mDevName（设备名称），mDevPSK（设备密钥）设置为null，还需修改 DataTemplateSample 初始化方法为 `public DataTemplateSample(String brokerURL, String productId, String devName, String devPSK, String devCertName, String devKeyName, TXMqttActionCallBack mqttActionCallBack,
final String jsonFileName,TXDataTemplateDownStreamCallBack downStreamCallBack)` ， 将证书放到resources文件夹中，在 DataTemplateSample 初始化时传入 devCertName（设备证书文件名称）devKeyName（设备私钥文件名称）。

## 子设备管理 ##
如果当前设备是一个网关，且该网关下的子设备需接入平台从而可以通过平台对子设备进行控制与管理，此时需要使用子设备管理功能。
网关子设备管理提供了添加子设备，删除子设备，子设备上线、子设备下线的能力。

```
  mGatewaySample.addSubDev(mSubDev1ProductId,mSubDev1DeviceName);
  //mGatewaySample.delSubDev(mSubDev1ProductId,mSubDev1DeviceName);
  
  mGatewaySample.onlineSubDev(mSubDev1ProductId,mSubDev1DeviceName);
  //mGatewaySample.offlineSubDev(mSubDev1ProductId,mSubDev1DeviceName);
```

### SDK设计说明

| 类名                 | 功能                                         |
| -------------------- | -------------------------------------------- |
| TXMqttConnection     | 连接物联网开发平台                           |
| TXDataTemplate       | 实现数据模板基本功能                         |
| TXDataTemplateClient | 实现直连设备根据数据模板连接物联网开发平台   |
| TXGatewayClient      | 实现网关设备根据数据模板连接物联网开发平台   |
| TXGatewaySubdev      | 实现网关子设备根据数据模板连接物联网开发平台 |

![](https://main.qcloudimg.com/raw/ea345acb67bd0f9ef20a7336704bd070.jpg)

## SDK API 说明

#### TXMqttConnection

| 方法名                        | 说明                                |
| ---------------------------- | -----------------------------------|
| connect                      |  MQTT 连接                          |
| reconnect                    |  MQTT 重连                          |
| disConnect                   |  断开 MQTT连接                       |
| publish                      |  发布 MQTT 消息                      |
| subscribe                    |  订阅 MQTT 主题                      |
| unSubscribe                  |  取消订阅 MQTT 主题                   |
| getConnectStatus             |  获取 MQTT 连接状态                   |
| setBufferOpts                |  设置断连状态 buffer 缓冲区              |
| initOTA                      |  初始化 OTA 功能                        |
| reportCurrentFirmwareVersion |  上报设备当前版本信息到后台服务器        |
| reportOTAState               |  上报设备升级状态到后台服务器            |

#### TXDataTemplate

| 方法名                   | 说明                     |
| ------------------------ | ------------------------ |
| subscribeTemplateTopic   | 订阅数据模板相关主题     |
| unSubscribeTemplateTopic | 取消订阅数据模板相关主题 |
| propertyReport           | 上报属性                 |
| propertyGetStatus        | 更新状态                 |
| propertyReportInfo       | 上报设备信息             |
| propertyClearControl     | 清除控制信息             |
| eventSinglePost          | 上报单个事件             |
| eventsPost               | 上报多个事件             |

#### TXDataTemplateClient

| 方法名                   | 说明                       |
| ------------------------ | -------------------------- |
| isConnected              | 是否已经连接物联网开发平台 |
| subscribeTemplateTopic   | 订阅数据模板相关主题       |
| unSubscribeTemplateTopic | 取消订阅数据模板相关主题   |
| propertyReport           | 上报属性                   |
| propertyGetStatus        | 更新状态                   |
| propertyReportInfo       | 上报设备信息               |
| propertyClearControl     | 清除控制信息               |
| eventSinglePost          | 上报单个事件               |
| eventsPost               | 上报多个事件               |

#### TXGatewayClient

| 方法名        | 说明                               |
| ------------- | ---------------------------------- |
| findSubdev    | 查找子设备（根据产品ID和设备名称） |
| removeSubdev  | 删除子设备                         |
| addSubdev     | 添加子设备                         |
| subdevOffline | 上线子设备                         |
| subdevOnline  | 下线子设备                         |
| setSubdevStatus  | 设置子设备状态             |
| subscribeSubDevTopic  | 订阅数据模板相关主题         |
| unSubscribeSubDevTopic  | 取消订阅数据模板相关主题         |
| subDevPropertyReport  | 属性上报                          |

#### TXGatewaySubdev

| 方法名          | 说明               |
| --------------- | ------------------ |
| getSubdevStatus | 获取子设备连接状态 |
| setSubdevStatus | 设置子设备连接状态 |
