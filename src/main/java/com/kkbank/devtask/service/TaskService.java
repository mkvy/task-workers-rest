package com.kkbank.devtask.service;

import com.kkbank.devtask.model.ShortTask;
import com.kkbank.devtask.model.Task;
import com.kkbank.devtask.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    private BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();

    @PostConstruct
    public void init() {
        startProcessingTasks();
    }

    public void SaveInQueue(Task task) {
        taskQueue.add(task);
    }

    private void startProcessingTasks() {
        new Thread(() -> {
            List<Task> tasks = new ArrayList<>();
            while (true) {
                Task task = taskQueue.poll();
                if (task != null) {
                    System.out.println("Adding task");
                    tasks.add(task);
                }
                if (!tasks.isEmpty() && tasks.size() >= 3) {
                    saveTasks(tasks);
                    System.out.println("saving tasks of");
                    tasks = new ArrayList<>();
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void saveTasks(List<Task> tasks) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (Task task : tasks) {
            executorService.submit(() -> Create(task));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public List<ShortTask> GetAllShortTasks() { return taskRepository.GetAllShorts(); }

    public void Create(Task model) {
        taskRepository.Create(model);
    }
    public Task GetByID(long id) {
        return taskRepository.GetByID(id);
    }

    public int Update(Task model) {
        return taskRepository.Update(model);
    }

    public int SetPerformer(long id, long performerId) {
        return taskRepository.UpdatePerformer(id, performerId);
    }

    public int Delete(long id) {
        return taskRepository.Delete(id);
    }

}
