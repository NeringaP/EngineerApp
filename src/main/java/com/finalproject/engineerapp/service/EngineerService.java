package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Engineer;

import java.util.List;
import java.util.Optional;

public interface EngineerService {
    List<Engineer> findAll();

    Engineer save(Engineer engineer);

    void delete(Engineer engineer);

    Long deleteById(Long id);

    Optional<Engineer> findById(Long id);
}
