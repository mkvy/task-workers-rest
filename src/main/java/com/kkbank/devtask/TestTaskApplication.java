package com.kkbank.devtask;

import com.kkbank.devtask.model.Worker;
import com.kkbank.devtask.repository.WorkerRepository;
import com.kkbank.devtask.service.WorkerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TestTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestTaskApplication.class, args);
	}

}
