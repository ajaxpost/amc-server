package com.amc.services.impl;

import com.amc.mapper.RouterMapper;
import com.amc.services.RouterServices;
import com.amc.web.domain.PvPOJO;
import com.amc.web.domain.TodayFlowPOJO;
import com.amc.web.domain.maptype.TodayDataType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RouterServicesImpl implements RouterServices {

    @Autowired
    private RouterMapper routerMapper;

    @Override
    public List<PvPOJO> findTimeList(String time) {
        return routerMapper.findTimeList(time);
    }


    @Override
    public int pvSave(PvPOJO pv) {
        return routerMapper.pvSave(pv);
    }


    private void initToDayList(List<TodayDataType> list, LocalDate now, Integer timeSize) {
        for (int i = 0; i < timeSize; i++) {
            list.add(new TodayDataType(now.minusDays(i).toString(), 0));
        }
    }

    private List<TodayDataType> getPvData(LocalDate now, LocalDate yester, Integer timeSize) {
        List<PvPOJO> pvPOJOS = routerMapper.selectPvByDay(yester.toString(), now.toString());
        ArrayList<TodayDataType> list = new ArrayList<>();
        initToDayList(list, now, timeSize);
        for (PvPOJO pvPOJO : pvPOJOS) {
            TodayDataType dataType = new TodayDataType();
            dataType.setDay(pvPOJO.getDay());
            dataType.setDayCount(pvPOJO.getDayCount());
            for (TodayDataType todayDataType : list) {
                if (todayDataType.getDay().equals(dataType.getDay())) {
                    todayDataType.setDayCount(dataType.getDayCount());
                }
            }
        }

        return list;
    }

    private List<TodayDataType> getUvData(LocalDate now, LocalDate yester, Integer timeSize) {
        List<PvPOJO> uvByDay = routerMapper.selectUvByDay(yester.toString(), now.toString());
        ArrayList<TodayDataType> list = new ArrayList<>();
        initToDayList(list, now, timeSize);
        for (PvPOJO pvPOJO : uvByDay) {
            TodayDataType dataType = new TodayDataType();
            dataType.setDay(pvPOJO.getDay());
            dataType.setDayCount(pvPOJO.getDayCount());
            for (TodayDataType todayDataType : list) {
                if (todayDataType.getDay().equals(dataType.getDay())) {
                    todayDataType.setDayCount(dataType.getDayCount());
                }
            }
        }
        return list;
    }

    private List<TodayDataType> getNewUvData(LocalDate now, LocalDate yester, Integer timeSize) {
        List<PvPOJO> newUvByDay = routerMapper.selectNewUvByDay(yester.toString(), now.toString());
        ArrayList<TodayDataType> list = new ArrayList<>();
        initToDayList(list, now, timeSize);
        for (PvPOJO pvPOJO : newUvByDay) {
            TodayDataType dataType = new TodayDataType();
            dataType.setDay(pvPOJO.getDay());
            dataType.setDayCount(pvPOJO.getDayCount());
            for (TodayDataType todayDataType : list) {
                if (todayDataType.getDay().equals(dataType.getDay())) {
                    todayDataType.setDayCount(dataType.getDayCount());
                }
            }
        }
        return list;
    }

    private List<TodayDataType> getIpData(LocalDate now, LocalDate yester, Integer timeSize) {
        List<PvPOJO> ipByDay = routerMapper.selectIpByDay(yester.toString(), now.toString());
        ArrayList<TodayDataType> list = new ArrayList<>();
        initToDayList(list, now, timeSize);
        for (PvPOJO pvPOJO : ipByDay) {
            TodayDataType dataType = new TodayDataType();
            dataType.setDay(pvPOJO.getDay());
            dataType.setDayCount(pvPOJO.getDayCount());
            for (TodayDataType todayDataType : list) {
                if (todayDataType.getDay().equals(dataType.getDay())) {
                    todayDataType.setDayCount(dataType.getDayCount());
                }
            }
        }
        return list;
    }

    @Override
    public TodayFlowPOJO getTodayFlowDataByTenMin(String pid) {
        LocalDate now = LocalDate.now();
        LocalDate yester = now.minusDays(1);
        TodayFlowPOJO todayFlowPOJO = new TodayFlowPOJO();
        List<TodayDataType> pvData = getPvData(now, yester, 2);
        List<TodayDataType> uvData = getUvData(now, yester, 2);
        List<TodayDataType> newUvData = getNewUvData(now, yester, 2);
        List<TodayDataType> ipData = getIpData(now, yester, 2);
        todayFlowPOJO.setTodayPvData(pvData);
        todayFlowPOJO.setTodayUvData(uvData);
        todayFlowPOJO.setTodayNewUvData(newUvData);
        todayFlowPOJO.setTodayIpData(ipData);
        return todayFlowPOJO;
    }

    @Override
    public Map<String, List<TodayDataType>> uvCountForMonth(String pid, Integer timeSize) {
        LocalDate now = LocalDate.now();
        LocalDate yester = now.minusDays(timeSize);
        HashMap<String, List<TodayDataType>> map = new HashMap<>();
        List<TodayDataType> uvData = getUvData(now, yester, timeSize);
        List<TodayDataType> newUvData = getNewUvData(now, yester, timeSize);
        map.put("uvData", uvData);
        map.put("newUvData", newUvData);
        return map;
    }

}
