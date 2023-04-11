package com.kkbank.devtask.controller;

import com.kkbank.devtask.model.ShortTask;
import com.kkbank.devtask.model.Task;
import com.kkbank.devtask.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired
    TaskService taskService;
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    @PostMapping("")
    public ResponseEntity<Void> Create(@RequestBody Task request) {
        log.debug("Received POST /api/v1/task request");
        taskService.SaveInQueue(request);
        return ResponseEntity.status(200).body(null);
    }

    @GetMapping("")
    public ResponseEntity<List<ShortTask>> GetShortList() {
        log.debug("Received GET /api/v1/task request");
        List<ShortTask> lst = taskService.GetAllShortTasks();
        if (lst.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(lst);

        }
        return ResponseEntity.ok(lst);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> GetByID(@PathVariable("id") long id) {
        Task task = taskService.GetByID(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> Delete(@PathVariable("id") long id) {
        int rowsDeleted = taskService.Delete(id);
        if (rowsDeleted == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rowsDeleted);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(rowsDeleted);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Integer> SetPerformer(@PathVariable("id") long id, @RequestBody Task request) {
        int rowsAffected = taskService.SetPerformer(id,request.getPerformerId());
        if (rowsAffected == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rowsAffected);
        }
        return ResponseEntity.status(200).body(rowsAffected);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> Update(@PathVariable("id") long id, @RequestBody Task request) {
        request.setId(id);
        int rowsAffected = taskService.Update(request);
        if (rowsAffected == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rowsAffected);
        }
        return ResponseEntity.status(200).body(rowsAffected);
    }

}
