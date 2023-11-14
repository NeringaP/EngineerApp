package com.finalproject.engineerapp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AuthorityId implements Serializable {
    @Column(name = "username")
    private String username;

    @Column(name = "authority")
    private String authority;

    // getters, setters, equals, and hashCode methods

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}

