package com.kkbank.devtask.repository;

import com.kkbank.devtask.mapper.ShortTaskMapper;
import com.kkbank.devtask.mapper.TaskMapper;
import com.kkbank.devtask.model.ShortTask;
import com.kkbank.devtask.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(TaskRepositoryImpl.class);
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void Create(Task model) {
        log.debug("Creating new task");
        jdbcTemplate.update("INSERT into tasks(title,description,time_t,status,performer_id) values (?,?,?,?,?)", model.getTitle(),model.getDescription(),model.getTime(),model.getStatus(),model.getPerformerId());
    }

    public Task GetByID(long id) {
        log.debug("Getting task by id ", id);
        Task result;
        try {
            result = jdbcTemplate.queryForObject("select * from tasks where id=?",
                    new Object[]{id},
                    new TaskMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return result;
    }

    public List<ShortTask> GetAllShorts() {
        log.debug("Getting all short tasks");
        return jdbcTemplate.query("select id, title, status from tasks", new ShortTaskMapper());
    }

    public int Update(Task model) {
        log.debug("Updating task");
        return jdbcTemplate.update("update tasks set title=?, description=?, time_t=?, status=? where id=?", model.getTitle(),model.getDescription(),model.getTime(),model.getStatus(),model.getId());
    }

    public int UpdatePerformer(long id, long performerId) {
        log.debug("Updating task with id ", id, " to performer_id ", performerId);
        return jdbcTemplate.update("update tasks set performer_id=? where id=?", performerId, id);
    }

    public int Delete(long id) {
        log.debug("Deleting task with id ", id);
        return jdbcTemplate.update("delete from tasks where id=?", id);
    }
}
