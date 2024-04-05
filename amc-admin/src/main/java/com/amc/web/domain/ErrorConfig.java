package com.amc.web.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@ToString
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL) // 来排除为null的字段,值为null的字段不会返回过去
@ApiModel(value = "错误信息", description = "错误信息")
public class ErrorConfig {
    @ApiModelProperty("对应的项目ID,也就是pid")
    private String apiKey;
    @ApiModelProperty("用户的ip")
    private String ip;
    @ApiModelProperty("用户的城市")
    private String city;
    @ApiModelProperty("用户的省份")
    private String province;
    @ApiModelProperty("错误的页面地址")
    private String pageUrl;
    @ApiModelProperty("sdkVersion")
    private String sdkVersion;
    @ApiModelProperty("错误类型")
    private String type;
    @ApiModelProperty("上报时间->时间戳")
    private Long time;
    @ApiModelProperty("探针生成的userId")
    private String userId;
    @ApiModelProperty("探针生成的routeId")
    private String routeId;
    @ApiModelProperty("探针生成的sessionId")
    private String sesId;
    @ApiModelProperty("探针生成的recordScreenId")
    private String recordScreenId;
    @ApiModelProperty("错误的文件行数")
    private Integer lineNumber;
    @ApiModelProperty("错误的文件列数")
    private Integer columnNumber;
    @ApiModelProperty("错误描述")
    private String errorMsg;
    @ApiModelProperty("错误堆栈")
    private String errorStack;
    @ApiModelProperty("设备信息")
    private DeviceInfo deviceInfo;
    @ApiModelProperty("用户行为轨迹")
    private List<Breadcrumb> breadCrumb;
    @ApiModelProperty("错误ID")
    private String errorId;
}
