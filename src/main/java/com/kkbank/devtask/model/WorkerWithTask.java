package com.kkbank.devtask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class WorkerWithTask {
    private Worker workerInfo;
    private ShortTask taskInfo;

}
