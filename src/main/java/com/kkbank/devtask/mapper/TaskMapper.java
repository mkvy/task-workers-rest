package com.kkbank.devtask.mapper;

import com.kkbank.devtask.model.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Task(rs.getLong("id"),rs.getString("title"),
                rs.getString("description"),
                rs.getTimestamp("time_t"),
                rs.getString("status"),
                rs.getLong("performer_id"));
    }
}
