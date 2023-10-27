package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Creator;
import com.finalproject.engineerapp.repositories.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreatorService {


    private CreatorRepository creatorRepository;
    @Autowired
    public CreatorService(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }

    public List<Creator> getCreators() {
        List<Creator> creators = creatorRepository.findAll();
        return creators;
    }

    public void updateCreator(Creator creator) {
        creatorRepository.save(creator);
    }

    public void addCreator(Creator creator) {
        creatorRepository.save(creator);
    }

    public void deleteCreator(Creator creator) {
        creatorRepository.delete(creator);
    }

    public Optional<Creator> findCreatorById(Long id) {
        Optional<Creator> creator = creatorRepository.findById(id);
        return creator;
    }
}
