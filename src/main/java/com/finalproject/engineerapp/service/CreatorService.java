package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Creator;

import java.util.List;
import java.util.Optional;

public interface CreatorService {
    List<Creator> findAll();

    void save(Creator creator);

    void delete(Creator creator);

    Optional<Creator> findById(Long id);
}
