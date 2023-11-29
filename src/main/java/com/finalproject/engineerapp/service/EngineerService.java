package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Engineer;

import java.util.List;
import java.util.Optional;

public interface EngineerService {
    List<Engineer> findAll();

    Engineer save(Engineer engineer);

    void delete(Long id);

    Engineer findById(Long id);
}
