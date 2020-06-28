package com.example.emt2_lab.domain.model.dtos;

import com.example.emt2_lab.domain.model.ClientAddress;
import com.example.emt2_lab.domain.model.ClientId;
import com.example.emt2_lab.domain.model.Payment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

public class ClientDto {

    public ClientIdDto id;
    public ClientAddressDto clientAddress;
    public String emailAddress;
    public String telephoneNumber;
    public boolean isActive;
    private Set<ProjectDto> projects;
    private Set<PaymentDto> payments;
}
