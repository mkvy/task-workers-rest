package com.kkbank.devtask.controller;

import com.kkbank.devtask.model.Worker;
import com.kkbank.devtask.model.WorkerWithTask;
import com.kkbank.devtask.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;


    @PostMapping("/")
    public ResponseEntity<Void> Create(@RequestBody Worker request) {
        return ResponseEntity.status(201).body(workerService.Create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> GetById(@PathVariable("id") long id) {
        Worker wrk = workerService.GetById(id);
        if (wrk == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(wrk);
    }

    @GetMapping("/")
    public ResponseEntity<List<Worker>> GetAll() {
        return ResponseEntity.ok(workerService.GetAll());
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<WorkerWithTask>> GetAllWithTasks() {
        return ResponseEntity.ok(workerService.GetAllWithTasks());
    }

    @PutMapping("/")
    public ResponseEntity<Integer> Update(@RequestBody Worker request) {
        int rowsUpdated = workerService.Update(request);
        if (rowsUpdated == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rowsUpdated);
        }
        return ResponseEntity.ok(rowsUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> Delete(@PathVariable("id") long id) {
        int rowsDeleted = workerService.Delete(id);
        if (rowsDeleted == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rowsDeleted);
        }
        return ResponseEntity.ok(rowsDeleted);
    }
}
