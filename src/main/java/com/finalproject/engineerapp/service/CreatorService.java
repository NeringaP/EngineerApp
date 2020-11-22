package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Creator;
import com.finalproject.engineerapp.repositories.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreatorService {

    @Autowired
    private CreatorRepository creatorService;

    public List<Creator> getCreators() {
        List<Creator> creators = creatorService.findAll();
        return creators;
    }

    public void updateCreator(Creator creator) {
        creatorService.save(creator);
    }

    public void addCreator(Creator creator) {
        creatorService.save(creator);
    }

    public void deleteCreator(Creator creator) {
        creatorService.delete(creator);
    }

    public Optional<Creator> findCreatorById(Long id) {
        Optional<Creator> creator = creatorService.findById(id);
        return creator;
    }
}
