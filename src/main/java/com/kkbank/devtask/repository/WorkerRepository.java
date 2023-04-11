package com.kkbank.devtask.repository;

import com.kkbank.devtask.model.Worker;
import com.kkbank.devtask.model.WorkerWithTask;

import java.util.List;

public interface WorkerRepository {
    void Create(Worker model);
    Worker GetById(long id);
    int Delete(long id);
    int Update(Worker model);
    List<Worker> GetAll();
    List<WorkerWithTask> GetAllWithTasks();

}
