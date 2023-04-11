package com.kkbank.devtask.repository;

import com.kkbank.devtask.mapper.WorkerMapper;
import com.kkbank.devtask.mapper.WorkerWithTaskMapper;
import com.kkbank.devtask.model.Worker;
import com.kkbank.devtask.model.WorkerWithTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.List;


@Repository
public class WorkerRepositoryImpl implements WorkerRepository {

    private static final Logger log = LoggerFactory.getLogger(WorkerRepositoryImpl.class);
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void Create(Worker model) {
        log.debug("Creating worker in database");

        jdbcTemplate.update("INSERT into workers(name_w,position_p,avatar_image) values (?, ?, ?)", model.getName(), model.getPosition(), model.getAvatarImage());
    }
    public Worker GetById(long id) {
        log.debug("Getting worker by id ", id);
        Worker result;
        try {
            result = jdbcTemplate.queryForObject("select * from workers where id = ?", new Object[]{id}, new WorkerMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return result;
    }

    public int Delete(long id) {
        log.debug("Deleting worker by id ", id);
        return jdbcTemplate.update("delete from workers where id=?", id);
    }

    public int Update(Worker model) {
        log.debug("Updating worker");
        return jdbcTemplate.update("update workers set name_w=?, position_p=?, avatar_image=? where id=?", model.getName(), model.getPosition(), model.getAvatarImage(), model.getId());
    }

    public List<Worker> GetAll() {
        log.debug("Getting all workers");
        return jdbcTemplate.query("select * from workers", new WorkerMapper());
    }

    public List<WorkerWithTask> GetAllWithTasks() {
        log.debug("Getting all workers joined with tasks");
        return jdbcTemplate.query("select w.id, w.name_w, w.position_p, w.avatar_image, t.id as task_id, t.title, t.status from workers w left join tasks t on t.performer_id=w.id"
                , new WorkerWithTaskMapper());
    }

}
