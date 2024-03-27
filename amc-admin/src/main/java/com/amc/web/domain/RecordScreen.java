package com.amc.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class RecordScreen {

    private String apiKey;
    private DeviceInfo deviceInfo;
    private String events;
    private String recordScreenId;
    private String sdkVersion;
    private Long time;
    private String userId;
    private String routeId;
    private String ip;
    private String province;
    private String city;
    private String pageUrl;


}
