package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Creator;

import java.util.List;
import java.util.Optional;

public interface CreatorService {
    List<Creator> findAll();

    Creator save(Creator creator);

    void delete(Long id);

    Optional<Creator> findById(Long id);
}
