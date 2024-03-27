package com.amc.typeHandler;

import com.amc.web.domain.DeviceInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(DeviceInfo.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class DeviceInfoTypeHandler extends BaseTypeHandler<DeviceInfo> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, DeviceInfo deviceInfo, JdbcType jdbcType) throws SQLException {
        System.out.println("转换执行了 deviceInfo");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            preparedStatement.setString(i, objectMapper.writeValueAsString(deviceInfo));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DeviceInfo getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public DeviceInfo getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public DeviceInfo getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
