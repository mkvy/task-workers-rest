package com.kkbank.devtask.mapper;

import com.kkbank.devtask.model.Worker;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkerMapper implements RowMapper<Worker> {
    @Override
    public Worker mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Worker(rs.getLong("id"), rs.getString("name_w"), rs.getString("position_p"), rs.getBytes("avatar_image"));
    }
}
