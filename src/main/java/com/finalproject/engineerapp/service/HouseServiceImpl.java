package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.House;
import com.finalproject.engineerapp.repositories.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HouseServiceImpl implements HouseService {

    private HouseRepository houseRepository;

    @Autowired
    public HouseServiceImpl(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Override
    public List<House> findAll() {
        List<House> houses = houseRepository.findAll();
        return houses;
    }

    @Override
    public void save(House house) {
        houseRepository.save(house);
    }

    @Override
    public void delete(Long id) {
        House house = findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " +
                "house Id:" + id));
        houseRepository.delete(house);
    }

    @Override
    public Optional<House> findById(Long id) {
        Optional<House> house = houseRepository.findById(id);
        return house;
    }
}