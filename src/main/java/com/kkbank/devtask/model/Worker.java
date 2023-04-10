package com.kkbank.devtask.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Worker {
    private long id;

    private String name;

    private String position;

    private byte[] avatarImage;
}

