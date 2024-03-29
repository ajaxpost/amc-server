package com.amc.web.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PvPOJO {

    private String apiKey;
    private String city;
    private Long entryTime; // 开始时间
    private String ip;
    private String pageAddress; // from
    private String pageName;
    private String pageUrl; // to
    private String province;
    private Long residenceTime; // 等待时间
    private String routeId;
    private String sesId;
    private String sdkVersion;
    private Long time; // 上报时间
    private String userId;
    private DeviceInfo deviceInfo;

}
