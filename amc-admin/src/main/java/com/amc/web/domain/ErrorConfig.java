package com.amc.web.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@ToString
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL) // 来排除为null的字段,值为null的字段不会返回过去
public class ErrorConfig {
    private String apiKey;
    private String ip;
    private String city;
    private String province;
    private String pageUrl;
    private String sdkVersion;
    private String type;
    private Long time;
    private String userId;
    private String routeId;
    private String sesId;
    private String recordScreenId;
    private Integer lineNumber;
    private Integer columnNumber;
    private String errorMsg;
    private String errorStack;
    private DeviceInfo deviceInfo;
    private List<Breadcrumb> breadCrumb;
    private String errorId;
}
