package com.amc.web.domain;

import com.amc.web.domain.maptype.TodayDataType;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class TodayFlowPOJO {

    private List<TodayDataType> todayPvData;
    private List<TodayDataType> todayUvData;
    private List<TodayDataType> todayNewUvData;
    private List<TodayDataType> todayIpData;

}


