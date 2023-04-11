package com.kkbank.devtask.mapper;

import com.kkbank.devtask.model.ShortTask;
import com.kkbank.devtask.model.Worker;
import com.kkbank.devtask.model.WorkerWithTask;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkerWithTaskMapper implements RowMapper<WorkerWithTask>{


    @Override
    public WorkerWithTask mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new WorkerWithTask(new Worker(rs.getLong("id"),
                rs.getString("name_w"),
                rs.getString("position_p"),
                rs.getBytes("avatar_image")),
                new ShortTask(rs.getLong("task_id"),rs.getString("title"),rs.getString("status"))
                );
    }
}
