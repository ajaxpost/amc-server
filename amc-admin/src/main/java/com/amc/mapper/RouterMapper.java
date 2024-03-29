package com.amc.mapper;

import com.amc.web.domain.PvPOJO;

import java.util.List;

public interface RouterMapper {

    int pvSave(PvPOJO pv);

    List<PvPOJO> findTimeList(String time); // 2024-03-29 这样的格式 ==> time

}
