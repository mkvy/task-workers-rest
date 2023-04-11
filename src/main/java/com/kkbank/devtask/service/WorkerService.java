package com.kkbank.devtask.service;

import com.kkbank.devtask.model.Worker;
import com.kkbank.devtask.model.WorkerWithTask;
import com.kkbank.devtask.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;

    public void Create(Worker model) {
        workerRepository.Create(model);
    }

    public List<Worker> GetAll() {
        return workerRepository.GetAll();
    }

    public int Delete(long id) {
        return workerRepository.Delete(id);
    }

    public Worker GetById(long id) {
        return workerRepository.GetById(id);
    }

    public int Update(Worker model) {
        return workerRepository.Update(model);
    }

    public List<WorkerWithTask> GetAllWithTasks() {
        return workerRepository.GetAllWithTasks();
    }

}
