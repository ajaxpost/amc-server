package com.amc.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class HttpPOJO {

    private String apiKey;
    private Long beginTime;
    private Long endTime;
    private String ip;
    private String city;
    private String province;
    private String pageUrl;
    private String routeId;
    private String sdkVersion;
    private String sesId;
    private Long time;
    private Integer timeStamp;
    private String type;
    private String userId;
    private DeviceInfo deviceInfo;
    private HttpInfo httpInfo;


}

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
class HttpInfo {
    private String businessId;
    private String method;
    private String requestText;
    private String responseContentType;
    private String responseText;
    private Integer status;
    private String statusText;
    private String url;
}