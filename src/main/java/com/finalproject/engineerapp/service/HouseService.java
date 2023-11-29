package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.House;

import java.util.List;
import java.util.Optional;

public interface HouseService {
    List<House> findAll();

    House save(House house);

    void delete(Long id) throws Exception;

    House findById(Long id) throws Exception;
}
