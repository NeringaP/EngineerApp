package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Authority;

import java.util.List;
import java.util.Optional;

public interface AuthoritiesService {
    List<Authority> findAll();

    void save(Authority authority);

    void delete(Authority authority);

    Optional<Authority> findById(Long id);
}
