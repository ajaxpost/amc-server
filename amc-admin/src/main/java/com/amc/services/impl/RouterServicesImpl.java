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
import java.util.List;

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


    private List<TodayDataType> getPvData() {
        LocalDate now = LocalDate.now();
        LocalDate yester = now.minusDays(1);
        List<PvPOJO> pvPOJOS = routerMapper.selectPvByDay(yester.toString(), now.toString());
        ArrayList<TodayDataType> list = new ArrayList<>();

        for (PvPOJO pvPOJO : pvPOJOS) {
            TodayDataType dataType = new TodayDataType();
            dataType.setDayName(pvPOJO.getDay());
            dataType.setDayCount(pvPOJO.getDayCount());
            list.add(dataType);
        }
        return list;
    }

    private List<TodayDataType> getUvData() {
        LocalDate now = LocalDate.now();
        LocalDate yester = now.minusDays(1);
        List<PvPOJO> uvByDay = routerMapper.selectUvByDay(yester.toString(), now.toString());
        ArrayList<TodayDataType> list = new ArrayList<>();
        for (PvPOJO pvPOJO : uvByDay) {
            TodayDataType dataType = new TodayDataType();
            dataType.setDayName(pvPOJO.getDay());
            dataType.setDayCount(pvPOJO.getDayCount());
            list.add(dataType);
        }
        return list;
    }

    private List<TodayDataType> getNewUvData() {
        LocalDate now = LocalDate.now();
        LocalDate yester = now.minusDays(1);
        List<PvPOJO> newUvByDay = routerMapper.selectNewUvByDay(yester.toString(), now.toString());
        ArrayList<TodayDataType> list = new ArrayList<>();
        for (PvPOJO pvPOJO : newUvByDay) {
            TodayDataType dataType = new TodayDataType();
            dataType.setDayName(pvPOJO.getDay());
            dataType.setDayCount(pvPOJO.getDayCount());
            list.add(dataType);
        }
        return list;
    }

    private List<TodayDataType> getIpData() {
        LocalDate now = LocalDate.now();
        LocalDate yester = now.minusDays(1);
        List<PvPOJO> ipByDay = routerMapper.selectIpByDay(yester.toString(), now.toString());
        ArrayList<TodayDataType> list = new ArrayList<>();
        for (PvPOJO pvPOJO : ipByDay) {
            TodayDataType dataType = new TodayDataType();
            dataType.setDayName(pvPOJO.getDay());
            dataType.setDayCount(pvPOJO.getDayCount());
            list.add(dataType);
        }
        return list;
    }

    @Override
    public TodayFlowPOJO getTodayFlowDataByTenMin(String pid) {
        TodayFlowPOJO todayFlowPOJO = new TodayFlowPOJO();
        List<TodayDataType> pvData = getPvData();
        List<TodayDataType> uvData = getUvData();
        List<TodayDataType> newUvData = getNewUvData();
        List<TodayDataType> ipData = getIpData();
        todayFlowPOJO.setTodayPvData(pvData);
        todayFlowPOJO.setTodayUvData(uvData);
        todayFlowPOJO.setTodayNewUvData(newUvData);
        todayFlowPOJO.setTodayIpData(ipData);
        return todayFlowPOJO;
    }

}
