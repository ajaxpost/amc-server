package com.amc.web.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HttpPOJO {

    private String httpId;
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
    private String status;
    private Integer count;
    private Double round;


}
