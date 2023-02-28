package com.example.claimsmicroservice.usermcroservices;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    private String nom;
    Long userId;
}
