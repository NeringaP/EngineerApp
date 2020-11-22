package com.finalproject.engineerapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne
    @JoinColumn(name = "engineer_id")
    @JsonBackReference
    private Engineer engineer;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    @JsonBackReference
    private Creator creator;

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

    public Engineer getEngineer() {
        return engineer;
    }

    public void setEngineer(Engineer engineer) {
        this.engineer = engineer;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }
}
