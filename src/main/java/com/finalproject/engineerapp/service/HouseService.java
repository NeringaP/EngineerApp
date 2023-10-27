package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.House;
import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.repositories.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseService {

    private HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public List<House> getHouses() {
        List<House> houses = houseRepository.findAll();
        return houses;
    }

    public void updateHouse(House house) {
        houseRepository.save(house);
    }

    public void addHouse(House house) {
        houseRepository.save(house);
    }

    public void deleteHouse(House house) {
        houseRepository.delete(house);
    }

    public Optional<House> findHouseById(Long id) {
        Optional<House> house = houseRepository.findById(id);
        return house;
    }
}
