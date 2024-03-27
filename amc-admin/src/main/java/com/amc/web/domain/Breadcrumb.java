package com.amc.web.domain;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@ToString
@Slf4j
/**
 * 用户行动轨迹
 */
public class Breadcrumb {
    private String type;
    private Long time;
    private String status;
    private String data;
}
