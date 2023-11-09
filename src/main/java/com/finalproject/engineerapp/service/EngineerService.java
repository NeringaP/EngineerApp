package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Engineer;

import java.util.List;
import java.util.Optional;

public interface EngineerService {
    List<Engineer> findAll();

    void save(Engineer engineer);

    void delete(Engineer engineer);

    Optional<Engineer> findById(Long id);
}
