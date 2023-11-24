package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.House;

import java.util.List;
import java.util.Optional;

public interface HouseService {
    List<House> findAll();

    void save(House house);

    void delete(Long id);

    Optional<House> findById(Long id);
}
