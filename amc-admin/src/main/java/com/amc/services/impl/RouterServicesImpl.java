package com.amc.services.impl;

import com.amc.mapper.RouterMapper;
import com.amc.services.RouterServices;
import com.amc.web.domain.CountByHour;
import com.amc.web.domain.PvPOJO;
import com.amc.web.domain.TodayFlowPOJO;
import com.amc.web.domain.maptype.HourDataType;
import com.amc.web.domain.maptype.TodayDataType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    private List<TodayDataType> getPvData(String pid, LocalDate now, LocalDate yester, Integer timeSize) {
        List<TodayDataType> pvByDay = routerMapper.selectPvByDay(pid, yester.toString(), now.toString());
        ArrayList<TodayDataType> list = new ArrayList<>();
        initToDayList(list, now, timeSize);
        for (TodayDataType item : pvByDay) {
            TodayDataType dataType = new TodayDataType();
            dataType.setDay(item.getDay());
            dataType.setDayCount(item.getDayCount());
            for (TodayDataType todayDataType : list) {
                if (todayDataType.getDay().equals(dataType.getDay())) {
                    todayDataType.setDayCount(dataType.getDayCount());
                }
            }
        }

        return list;
    }

    private List<TodayDataType> getUvData(String pid, LocalDate now, LocalDate yester, Integer timeSize) {
        List<TodayDataType> uvByDay = routerMapper.selectUvByDay(pid, yester.toString(), now.toString());
        ArrayList<TodayDataType> list = new ArrayList<>();
        initToDayList(list, now, timeSize);
        for (TodayDataType item : uvByDay) {
            TodayDataType dataType = new TodayDataType();
            dataType.setDay(item.getDay());
            dataType.setDayCount(item.getDayCount());
            for (TodayDataType todayDataType : list) {
                if (todayDataType.getDay().equals(dataType.getDay())) {
                    todayDataType.setDayCount(dataType.getDayCount());
                }
            }
        }
        return list;
    }

    private List<TodayDataType> getNewUvData(String pid, LocalDate now, LocalDate yester, Integer timeSize) {
        List<TodayDataType> newUvByDay = routerMapper.selectNewUvByDay(pid, yester.toString(), now.toString());
        ArrayList<TodayDataType> list = new ArrayList<>();
        initToDayList(list, now, timeSize);
        for (TodayDataType item : newUvByDay) {
            TodayDataType dataType = new TodayDataType();
            dataType.setDay(item.getDay());
            dataType.setDayCount(item.getDayCount());
            for (TodayDataType todayDataType : list) {
                if (todayDataType.getDay().equals(dataType.getDay())) {
                    todayDataType.setDayCount(dataType.getDayCount());
                }
            }
        }
        return list;
    }

    private List<TodayDataType> getIpData(String pid, LocalDate now, LocalDate yester, Integer timeSize) {
        List<TodayDataType> ipByDay = routerMapper.selectIpByDay(pid, yester.toString(), now.toString());
        ArrayList<TodayDataType> list = new ArrayList<>();
        initToDayList(list, now, timeSize);
        for (TodayDataType item : ipByDay) {
            TodayDataType dataType = new TodayDataType();
            dataType.setDay(item.getDay());
            dataType.setDayCount(item.getDayCount());
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
        List<TodayDataType> pvData = getPvData(pid, now, yester, 2);
        List<TodayDataType> uvData = getUvData(pid, now, yester, 2);
        List<TodayDataType> newUvData = getNewUvData(pid, now, yester, 2);
        List<TodayDataType> ipData = getIpData(pid, now, yester, 2);
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
        List<TodayDataType> uvData = getUvData(pid, now, yester, timeSize);
        List<TodayDataType> newUvData = getNewUvData(pid, now, yester, timeSize);
        map.put("uvData", uvData);
        map.put("newUvData", newUvData);
        return map;
    }

    /**
     * 辅助方法,用户初始化某一天时间的24小时数据
     * *      假设当前时间为 2024-04-07 15:20:00
     * *      那么头部时间应该是 04-06 16:00 -- 04-07 15:00
     * *      合计一共24位数
     *
     * @param now
     */
    private HourDataType[] initToHourArr(LocalDateTime now) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        HourDataType[] hourDataTypes = new HourDataType[24];
        for (int i = 0; i < hourDataTypes.length; i++) {
            LocalDateTime dateTime = now.minusHours(i);
            String format1 = dateTime.format(formatter);
            HourDataType hourDataType = new HourDataType(format1, 0);
            hourDataTypes[i] = hourDataType;
        }

        return hourDataTypes;
    }

    private List<HourDataType> transformToHourList(List<HourDataType> list, HourDataType[] hourDataTypes) {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        for (HourDataType item : list) {
            String hour = item.getHour();
            for (HourDataType hourDataType : hourDataTypes) {
                String s = hourDataType.getHour().replace(year + "-", "");
                if (s.equals(hour)) {
                    hourDataType.setCount(item.getCount());
                }
            }
        }
        return new ArrayList<>(Arrays.asList(hourDataTypes));
    }

    @Override
    public CountByHour getPvCountByHour(String pid, Integer scope) {
        LocalDateTime now = LocalDateTime.now();
        HourDataType[] today = initToHourArr(now);
        HourDataType[] yester = initToHourArr(now.minusDays(scope));

        // 获取数组中第一个和最后一个时间
        // 第一个 > 最后一个
        List<HourDataType> todayData = routerMapper.selectPvByHour(pid, today[today.length - 1].getHour(), today[0].getHour());

        List<HourDataType> _todayData = transformToHourList(todayData, today);
        _todayData.sort(Comparator.comparing(HourDataType::getHour));

        List<HourDataType> yesterData = routerMapper.selectPvByHour(pid, yester[yester.length - 1].getHour(), yester[0].getHour());
        List<HourDataType> _yesterData = transformToHourList(yesterData, yester);
        _yesterData.sort(Comparator.comparing(HourDataType::getHour));

        return new CountByHour(_yesterData, _todayData);


    }

    @Override
    public CountByHour getUvCountByHour(String pid, Integer scope) {
        LocalDateTime now = LocalDateTime.now();
        HourDataType[] today = initToHourArr(now);
        HourDataType[] yester = initToHourArr(now.minusDays(scope));
        List<HourDataType> todayData = routerMapper.selectUvByHour(pid, today[today.length - 1].getHour(), today[0].getHour());
        List<HourDataType> _todayData = transformToHourList(todayData, today);
        _todayData.sort(Comparator.comparing(HourDataType::getHour));

        List<HourDataType> yesterData = routerMapper.selectUvByHour(pid, yester[yester.length - 1].getHour(), yester[0].getHour());
        List<HourDataType> _yesterData = transformToHourList(yesterData, yester);
        _yesterData.sort(Comparator.comparing(HourDataType::getHour));
        return new CountByHour(_yesterData, _todayData);
    }

    @Override
    public CountByHour getNewCustomerCountByHour(String pid, Integer scope) {
        LocalDateTime now = LocalDateTime.now();
        HourDataType[] today = initToHourArr(now);
        HourDataType[] yester = initToHourArr(now.minusDays(scope));
        List<HourDataType> todayData = routerMapper.selectNewUvByHour(pid, today[today.length - 1].getHour(), today[0].getHour());
        List<HourDataType> _todayData = transformToHourList(todayData, today);
        _todayData.sort(Comparator.comparing(HourDataType::getHour));

        List<HourDataType> yesterData = routerMapper.selectNewUvByHour(pid, yester[yester.length - 1].getHour(), yester[0].getHour());
        List<HourDataType> _yesterData = transformToHourList(yesterData, yester);
        _yesterData.sort(Comparator.comparing(HourDataType::getHour));
        return new CountByHour(_yesterData, _todayData);
    }

}
