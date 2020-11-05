package com.finalproject.engineerapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<House> houses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<House> getHouses() {
        return houses;
    }

    public void setHouses(Set<House> houses) {
        this.houses = houses;
    }
}
