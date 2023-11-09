package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Engineer;
import com.finalproject.engineerapp.repositories.EngineerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EngineerServiceImpl implements EngineerService {

    private EngineerRepository engineerRepository;

    @Autowired
    public EngineerServiceImpl(EngineerRepository engineerRepository) {
        this.engineerRepository = engineerRepository;
    }

    @Override
    public List<Engineer> findAll() {
        List<Engineer> engineers = engineerRepository.findAll();
        return engineers;
    }

    @Override
    @Transactional
    public void save(Engineer engineer) {
        engineerRepository.save(engineer);
    }

    @Override
    @Transactional
    public void delete(Engineer engineer) {
        engineerRepository.delete(engineer);
    }

    @Override
    public Optional<Engineer> findById(Long id) {
        Optional<Engineer> engineer = engineerRepository.findById(id);
        return engineer;
    }
}