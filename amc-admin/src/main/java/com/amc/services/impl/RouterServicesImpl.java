package com.amc.services.impl;

import com.amc.mapper.RouterMapper;
import com.amc.services.RouterServices;
import com.amc.web.domain.PvPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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


}
