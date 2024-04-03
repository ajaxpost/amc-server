package com.amc.web.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL) // 来排除为null的字段,值为null的字段不会返回过去
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
    private String day; // 额外字段,统计PV
    private Integer dayCount; // 额外字段,统计PV

}
