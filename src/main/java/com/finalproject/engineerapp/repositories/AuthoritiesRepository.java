package com.finalproject.engineerapp.repositories;

import com.finalproject.engineerapp.model.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthoritiesRepository extends CrudRepository<Authority, Long> {
    List<Authority> findAll();
}
