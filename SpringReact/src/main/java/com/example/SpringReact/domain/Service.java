package com.example.SpringReact.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Service {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String service;

    @ManyToMany(mappedBy = "locationServices")
    private Set<Location> locations;


    }
