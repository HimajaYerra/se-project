package com.example.SpringReact.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

//Lombok
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity //  mapping an object state to database column
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // use the auto increment of the database
    private Long id;
    private String service;
    private String location;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    private String slot;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
