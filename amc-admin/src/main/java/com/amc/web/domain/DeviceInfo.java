package com.amc.web.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeviceInfo {

    private String browser;
    private String browserVersion;
    private String os;
    private String ua;

}
