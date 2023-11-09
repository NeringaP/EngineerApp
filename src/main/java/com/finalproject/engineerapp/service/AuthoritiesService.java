package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Authorities;

import java.util.List;
import java.util.Optional;

public interface AuthoritiesService {
    List<Authorities> findAll();

    void save(Authorities authorities);

    void delete(Authorities authorities);

    Optional<Authorities> findById(Long id);
}
