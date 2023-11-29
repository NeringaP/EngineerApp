package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Creator;
import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.repositories.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Creator save(Creator creator) {
       return creatorRepository.save(creator);
    }

    @Override
    public void delete(Long id) {
        Creator creator = findById(id);
        Set<Project> projects = creator.getProjects();
        for (Project project : projects) {
            project.setCreator(null);
        }
        creatorRepository.delete(creator);
    }

    @Override
    public Creator findById(Long id) {
        Creator creator = creatorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " +
                "creator Id:" + id));
        return creator;
    }
}