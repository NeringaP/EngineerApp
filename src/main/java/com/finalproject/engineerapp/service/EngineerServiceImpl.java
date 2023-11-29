package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Engineer;
import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.repositories.EngineerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Engineer save(Engineer engineer) {
        return engineerRepository.save(engineer);
    }

    @Override
    public void delete(Long id) {
        Engineer engineer = findById(id);
        Set<Project> projects = engineer.getProjects();
        for (Project project : projects) {
            project.setEngineer(null);
        }
        engineerRepository.delete(engineer);
    }

    @Override
    public Engineer findById(Long id) {
        Engineer engineer = engineerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " +
                "engineer Id:" + id));
        return engineer;
    }
}