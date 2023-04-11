package com.kkbank.devtask.controller;

import com.kkbank.devtask.model.Worker;
import com.kkbank.devtask.model.WorkerWithTask;
import com.kkbank.devtask.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    private static final Logger log = LoggerFactory.getLogger(WorkerController.class);
    @PostMapping("")
    public ResponseEntity<Void> Create(@RequestBody Worker request) {
        log.debug("Received POST /api/v1/worker request");
        workerService.Create(request);
        return ResponseEntity.status(201).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> GetById(@PathVariable("id") long id) {
        log.debug("Received GET /api/v1/worker/{id} request");
        Worker wrk = workerService.GetById(id);
        if (wrk == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(wrk);
    }

    @GetMapping("")
    public ResponseEntity<List<Worker>> GetAll() {

        log.debug("Received GET /api/v1/worker request");
        return ResponseEntity.ok(workerService.GetAll());
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<WorkerWithTask>> GetAllWithTasks() {
        log.debug("Received GET /api/v1/worker?tasks=true request");
        return ResponseEntity.ok(workerService.GetAllWithTasks());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> Update(@PathVariable("id") long id, @RequestBody Worker request) {
        log.debug("Received PUT /api/v1/worker/{id} request");
        request.setId(id);
        int rowsUpdated = workerService.Update(request);
        if (rowsUpdated == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rowsUpdated);
        }
        return ResponseEntity.ok(rowsUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> Delete(@PathVariable("id") long id) {
        log.debug("Received DELETE /api/v1/worker/{id} request");
        int rowsDeleted = workerService.Delete(id);
        if (rowsDeleted == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rowsDeleted);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(rowsDeleted);
    }
}
