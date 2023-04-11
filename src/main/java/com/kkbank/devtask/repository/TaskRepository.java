package com.kkbank.devtask.repository;

import com.kkbank.devtask.model.ShortTask;
import com.kkbank.devtask.model.Task;

import java.util.List;

public interface TaskRepository {
    void Create(Task model);
    Task GetByID(long id);
    List<ShortTask> GetAllShorts();
    int Update(Task model);
    int UpdatePerformer(long id, long performerId);

    int Delete(long id);
}
