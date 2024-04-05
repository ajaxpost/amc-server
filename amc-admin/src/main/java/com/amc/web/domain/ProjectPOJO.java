package com.amc.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "ProjectPOJO", description = "项目信息")
public class ProjectPOJO {

    @ApiModelProperty(value = "项目id")
    private String projectId;
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "项目描述")
    private String projectDesc;
    @ApiModelProperty(value = "创建时间")
    private String createTime;


}
