package com.example.emt2_lab.domain.model.dtos;

import java.time.LocalDateTime;

public class TaskDto {

    public TaskIdDto id;
    public String name;
    public LocalDateTime startDate;
    public LocalDateTime deadline;
    public String description;
    public Integer hours;
    public MoneyDto price;
    public boolean finished;
    public ProjectDto project;

}
