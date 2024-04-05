package com.amc.web.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL) // 来排除为null的字段,值为null的字段不会返回过去
@ApiModel("PV信息")
public class PvPOJO {

    @ApiModelProperty("对应的项目id,==>pid")
    private String apiKey;
    @ApiModelProperty("城市")
    private String city;
    @ApiModelProperty("pv开始时间,也就是进入页面的时间")
    private Long entryTime; // 开始时间
    @ApiModelProperty("ip")
    private String ip;
    @ApiModelProperty("从哪个页面过来的")
    private String pageAddress; // from
    @ApiModelProperty("页面名称")
    private String pageName;
    @ApiModelProperty("要去的页面地址")
    private String pageUrl; // to
    @ApiModelProperty("省份")
    private String province;
    @ApiModelProperty("在页面停留时间")
    private Long residenceTime; // 等待时间
    @ApiModelProperty("路由id")
    private String routeId;
    @ApiModelProperty("会话id")
    private String sesId;
    @ApiModelProperty("skdVersion")
    private String sdkVersion;
    @ApiModelProperty("上报时间")
    private Long time; // 上报时间
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("浏览器信息")
    private DeviceInfo deviceInfo;

}
