package com.example.emt2_lab.domain.model.dtos;

import com.example.emt2_lab.domain.model.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

public class ProjectDto {

    public ProjectIdDto id;
    public String name;
    public String description;
    public LocalDateTime startDate;
    public LocalDateTime deadline;
    public boolean finished;
    public boolean payed;
    public MoneyDto price;
    public Set<TaskDto> tasks;
    public Set<ClientDto> clients;

}
