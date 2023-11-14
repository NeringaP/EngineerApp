package com.finalproject.engineerapp.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class Authority {

    @EmbeddedId
    private AuthorityId id;

    @OneToOne
    @JoinColumn(name="username")
    @MapsId("username")
    private User user;

    public AuthorityId getId() {
        return id;
    }

    public void setId(AuthorityId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
