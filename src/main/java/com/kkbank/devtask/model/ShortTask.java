package com.kkbank.devtask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@ToString
@Getter
public class ShortTask {
    private long taskId;

    private String title;

    private String status;
}
