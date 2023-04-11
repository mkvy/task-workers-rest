package com.kkbank.devtask.mapper;

import com.kkbank.devtask.model.ShortTask;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShortTaskMapper implements RowMapper<ShortTask> {

    @Override
    public ShortTask mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ShortTask(rs.getLong("id"),rs.getString("title"),rs.getString("status"));
    }
}
