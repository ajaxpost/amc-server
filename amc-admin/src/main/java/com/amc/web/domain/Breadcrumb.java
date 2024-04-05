package com.amc.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@ToString
@Slf4j
@ApiModel("用户行为轨迹")
/**
 * 用户行动轨迹
 */
public class Breadcrumb {
    @ApiModelProperty("用户行为类型")
    private String type;
    @ApiModelProperty("用户行为时间")
    private Long time;
    @ApiModelProperty("用户行为状态")
    private String status;
    @ApiModelProperty("用户行为数据")
    private String data;
}
