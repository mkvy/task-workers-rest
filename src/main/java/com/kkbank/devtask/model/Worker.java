package com.kkbank.devtask.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Worker {
    private long id;

    @JsonProperty("name_w")
    private String name;

    @JsonProperty("position_p")
    private String position;

    @JsonProperty("avatar_image")
    private byte[] avatarImage;
}

