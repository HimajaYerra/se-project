package com.example.SpringReact.domain;

import lombok.*;

import javax.persistence.Entity;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Login {

    private String name;
    private String password;

}