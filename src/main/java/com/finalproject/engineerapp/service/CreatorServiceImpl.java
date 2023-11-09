package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Creator;
import com.finalproject.engineerapp.repositories.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CreatorServiceImpl implements CreatorService {

    private CreatorRepository creatorRepository;

    @Autowired
    public CreatorServiceImpl(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }

    @Override
    public List<Creator> findAll() {
        List<Creator> creators = creatorRepository.findAll();
        return creators;
    }

    @Override
    @Transactional
    public void save(Creator creator) {
        creatorRepository.save(creator);
    }

    @Override
    @Transactional
    public void delete(Creator creator) {
        creatorRepository.delete(creator);
    }

    @Override
    public Optional<Creator> findById(Long id) {
        Optional<Creator> creator = creatorRepository.findById(id);
        return creator;
    }
}