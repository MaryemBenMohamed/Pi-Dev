package com.example.claimsmicroservice.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Claim implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Temporal(TemporalType.DATE)
    Date dateDiff;
    @Temporal(TemporalType.DATE)
    Date dateRes;
    String title;
    String description;
    String cause;
    @Enumerated(EnumType.STRING)
    Status status;
    @Enumerated(EnumType.STRING)
    TypeReclamation typeReclamation;
    String username;





}