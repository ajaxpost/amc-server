package com.amc.services.impl;

import com.amc.mapper.ScreenMapper;
import com.amc.services.ScreenServices;
import com.amc.web.domain.RecordScreen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScreenServicesImpl implements ScreenServices {

    @Autowired
    private ScreenMapper screenMapper;

    @Override
    public int save(RecordScreen recordScreen) {
        return screenMapper.save(recordScreen);
    }
}
