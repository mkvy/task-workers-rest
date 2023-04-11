package com.kkbank.devtask.repository;

import com.kkbank.devtask.exception.NotFoundException;
import com.kkbank.devtask.mapper.ShortTaskMapper;
import com.kkbank.devtask.mapper.TaskMapper;
import com.kkbank.devtask.model.ShortTask;
import com.kkbank.devtask.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void Create(Task model) {
        jdbcTemplate.update("INSERT into tasks(title,description,time_t,status,performer_id) values (?,?,?,?,?)", model.getTitle(),model.getDescription(),model.getTime(),model.getStatus(),model.getPerformerId());
    }

    public Task GetByID(long id) {
        Task result;
        try {
            result = jdbcTemplate.queryForObject("select * from tasks where id=?",
                    new Object[]{id},
                    new TaskMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Task not found by id", e);
        }
        return result;
    }

    public List<ShortTask> GetAllShorts() {
        return jdbcTemplate.query("select id, title, status from tasks", new ShortTaskMapper());
    }

    public int Update(Task model) {
        int updatedRows;
        try {
            updatedRows = jdbcTemplate.update("update tasks set title=?, description=?, time_t=?, status=? where id=?", model.getTitle(),model.getDescription(),model.getTime(),model.getStatus(),model.getId());
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Task not found by id", e);
        }
        return updatedRows;
    }

    public int UpdatePerformer(long id, long performerId) {
        int updatedRows;
        try {
            updatedRows = jdbcTemplate.update("update tasks set performer_id=? where id=?", performerId, id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Task not found by id", e);
        }
        return updatedRows;
    }

    public int Delete(long id) {
        int rowsDeleted;
        try {
            rowsDeleted = jdbcTemplate.update("delete from tasks where id=?", id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Task not found by id", e);
        }
        return rowsDeleted;
    }
}
