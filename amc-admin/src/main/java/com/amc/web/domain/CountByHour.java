package com.amc.web.domain;

import com.amc.web.domain.maptype.HourDataType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("统计信息")
public class CountByHour {
    @ApiModelProperty("上一周的数据")
    private List<HourDataType> seven;
    @ApiModelProperty("当天的数据")
    private List<HourDataType> today;
}
