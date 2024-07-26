package com.amc.services.impl;

import com.amc.core.DayUtils;
import com.amc.mapper.HttpMapper;
import com.amc.services.HttpServices;
import com.amc.web.controller.performance.Per;
import com.amc.web.domain.HttpPOJO;
import com.amc.web.domain.maptype.DayDataType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HttpServicesImpl implements HttpServices {

    @Autowired
    private HttpMapper httpMapper;
    @Autowired
    private Per per;

    @Override
    public int save(List<HttpPOJO> list) {
        return httpMapper.save(list);
    }

    @Override
    public Map<String, List<DayDataType>> getHttpErrorCountByDay(String pid, String startDate, String endDate) {
        List<HttpPOJO> https = httpMapper.getHttpErrorCountByDay(pid, startDate, endDate);
        https.sort(Comparator.comparing(HttpPOJO::getTime));
        ArrayList<DayDataType> data = new ArrayList<>();
        ArrayList<DayDataType> perData = new ArrayList<>();
        for (HttpPOJO http : https) {
            DayDataType dayDataType = new DayDataType();
            DayDataType perDayDataType = new DayDataType();
            String day = DayUtils.timestamptoDateString(http.getTime(), "MM-dd");
            List<DayDataType> collect = data.stream().filter((d) ->
                    d.getDay().equals(day)).collect(Collectors.toList());


            if (!collect.isEmpty()) {
                DayDataType dayDataType1 = collect.get(0);
                dayDataType1.setCount((Integer) (dayDataType1.getCount()) + http.getCount());
                List<DayDataType> collect1 = perData.stream().filter((d) ->
                        d.getDay().equals(day)).collect(Collectors.toList());
                if (!collect1.isEmpty()) {
                    DayDataType dayDataType2 = collect1.get(0);
                    dayDataType2.setCount((Double) (dayDataType2.getCount()) + http.getRound());
                }
                continue;

            }
            dayDataType.setDay(day);
            dayDataType.setCount(http.getCount());
            data.add(dayDataType);
            perDayDataType.setDay(day);
            perDayDataType.setCount(http.getRound());
            perData.add(perDayDataType);
        }
        Map<String, List<DayDataType>> map = new HashMap<>();
        map.put("data", data);
        map.put("perData", perData);
        return map;
    }

    @Override
    public List<Map<String, String>> getStatusListGroupByErrorCode(String pid, String date) {
        List<HttpPOJO> https = httpMapper.getStatusListGroupByErrorCode(pid, date);
        ArrayList<Map<String, String>> maps = new ArrayList<>();
        for (HttpPOJO http : https) {
            Map<String, String> map = new HashMap<>();
            map.put("status", http.getStatus());
            map.put("count", String.valueOf(http.getCount()));
            maps.add(map);
        }
        return maps;
    }

    @Override
    public List<HttpPOJO> getHttpOverflow(String pid, String startDate, String endDate) {
        return httpMapper.getHttpOverflow(pid, startDate, endDate);
    }

    @Override
    public List<HttpPOJO> getHttpTop(String pid) {
        return httpMapper.getHttpTop(pid);
    }
}

