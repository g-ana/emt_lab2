package com.example.emt2_lab.domain.model.dtos;

import com.example.emt2_lab.domain.model.ClientAddress;
import com.example.emt2_lab.domain.model.PaymentId;
import com.example.emt2_lab.domain.model.PaymentState;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.time.Instant;

public class PaymentDto {

    public PaymentId id;
    public Instant payedOn;
    public PriceDto price;
    public PaymentState state;
    public ClientAddressDto clientAddress;
    public Set<ProjectDto> projects;

}
