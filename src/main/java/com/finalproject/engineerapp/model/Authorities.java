package com.finalproject.engineerapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="authorities")
public class Authorities {

    @EmbeddedId
    private AuthoritiesId id;

    @ManyToOne
    @JoinColumn(name="username")
    @MapsId("username")
    private User user;

    public AuthoritiesId getId() {
        return id;
    }

    public void setId(AuthoritiesId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
