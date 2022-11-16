package com.example.SpringReact.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String cityState;


  /*  @ManyToMany
    @JoinTable(
            name = "location_services",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<Service> locationServices;*/

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationId")
    private List<SlotData> slots;

}
