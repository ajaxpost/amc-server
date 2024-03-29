package com.amc.web.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PerConfig {
    /**
     * 性能监控5大指标名称
     */
    private String name;

    /**
     * 页面地址
     */
    private String pageUrl;

    /**
     * sdk 版本
     */
    private String sdkVersion;

    /**
     * 上报时间
     */
    private Long time;


    /**
     * 用户id
     */
    private String userId;


    private String routeId;
    private String sesId;

    private Integer value;

    private DeviceInfo deviceInfo;

    /**
     * 项目key
     */
    private String apiKey;

    private String city;
    private String ip;
    private String province;

}
