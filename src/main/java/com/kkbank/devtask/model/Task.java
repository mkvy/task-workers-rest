package com.kkbank.devtask.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;


@AllArgsConstructor
@ToString
@Getter
@Setter
public class Task {

    private long id;

    private String title;

    private String description;

    @JsonProperty("time_t")
    private Timestamp time;

    private String status;

    @JsonProperty("performer_id")
    private long performerId;

}

