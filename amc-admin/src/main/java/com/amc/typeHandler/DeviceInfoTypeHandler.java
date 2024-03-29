package com.amc.typeHandler;

import com.amc.web.domain.DeviceInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*@MappedTypes(DeviceInfo.class)
@MappedJdbcTypes(JdbcType.VARCHAR)*/
public class DeviceInfoTypeHandler extends BaseTypeHandler<DeviceInfo> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, DeviceInfo deviceInfo, JdbcType jdbcType) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            preparedStatement.setString(i, objectMapper.writeValueAsString(deviceInfo));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DeviceInfo getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(string, DeviceInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DeviceInfo getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(string, DeviceInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DeviceInfo getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(string, DeviceInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
