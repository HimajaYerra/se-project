package com.example.SpringReact.domain;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @JsonAlias(value = "first_name")
    private String firstName;

    @JsonAlias(value = "last_name")
    private String lastName;

    @JsonAlias(value = "email_id")
    private String emailId;

    private String password;

    private Date dob;

    private String ssn;

    private String telephone;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Appointment> appointments;

}
