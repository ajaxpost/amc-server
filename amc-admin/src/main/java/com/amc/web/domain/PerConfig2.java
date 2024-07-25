package com.amc.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PerConfig2 {

    private Integer id;
    private String apiKey;
    private Integer dns;
    private Integer tcp;
    private Integer ssl;
    private Long time;
    private Integer response;
    private Integer transfer;
    private Integer domParse;
    private Integer resource;
    private Integer loadPage;
    private String city;
    private String ip;
    private String pageUrl;
    private String province;
    private String routeId;
    private String sdkVersion;
    private String sesId;
    private String userId;
    private DeviceInfo deviceInfo;

}
