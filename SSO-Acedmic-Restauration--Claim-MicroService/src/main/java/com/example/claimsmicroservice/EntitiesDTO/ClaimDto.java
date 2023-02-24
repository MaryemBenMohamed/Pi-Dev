package com.example.claimsmicroservice.EntitiesDTO;

import com.example.claimsmicroservice.entities.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.io.Serializable;


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClaimDto {
    Status status;
}
