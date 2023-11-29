package com.finalproject.engineerapp.repository;

import com.finalproject.engineerapp.model.House;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends CrudRepository<House, Long> {
    List<House> findAll();
}
