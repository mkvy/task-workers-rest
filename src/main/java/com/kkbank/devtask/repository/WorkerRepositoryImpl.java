package com.kkbank.devtask.repository;

import com.kkbank.devtask.exception.NotFoundException;
import com.kkbank.devtask.mapper.WorkerMapper;
import com.kkbank.devtask.mapper.WorkerWithTaskMapper;
import com.kkbank.devtask.model.Worker;
import com.kkbank.devtask.model.WorkerWithTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.List;


@Repository
public class WorkerRepositoryImpl implements WorkerRepository {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void Create(Worker model) {
        jdbcTemplate.update("INSERT into workers(name_w,position_p,avatar_image) values (?, ?, ?)", model.getName(), model.getPosition(), model.getAvatarImage());
    }
    public Worker GetById(long id) {
        Worker result;
        try {
            result = jdbcTemplate.queryForObject("select * from workers where id = ?", new Object[]{id}, new WorkerMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Worker not found by id", e);
        }
        return result;
    }

    public int Delete(long id) {
        int rowsDeleted;
        try {
            rowsDeleted = jdbcTemplate.update("delete from workers where id=?", id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Worker not found by id", e);
        }
        return rowsDeleted;
    }

    public int Update(Worker model) {
        int updatedRows;
        try {
            updatedRows = jdbcTemplate.update("update workers set name_w=?, position_p=?, avatar_image=? where id=?", model.getName(), model.getPosition(), model.getAvatarImage(), model.getId());
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Worker not found by id", e);
        }
        return updatedRows;
    }

    public List<Worker> GetAll() {
        return jdbcTemplate.query("select * from workers", new WorkerMapper());
    }

    public List<WorkerWithTask> GetAllWithTasks() {
        return jdbcTemplate.query("select w.id, w.name_w, w.position_p, w.avatar_image, t.id as task_id, t.title, t.status from workers w left join tasks t on t.performer_id=w.id"
                , new WorkerWithTaskMapper());
    }

}
