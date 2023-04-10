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
    private long id;

    private String name;

    private String position;

    private byte[] avatarImage;
    private ShortTask taskInfo;

}
