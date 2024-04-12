package com.amc.typeHandler;

import com.amc.web.domain.HttpInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HttpInfoTypeHandler extends BaseTypeHandler<HttpInfo> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, HttpInfo parameter, JdbcType jdbcType) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ps.setString(i, objectMapper.writeValueAsString(parameter));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HttpInfo getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(string, HttpInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HttpInfo getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(string, HttpInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HttpInfo getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(string, HttpInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
