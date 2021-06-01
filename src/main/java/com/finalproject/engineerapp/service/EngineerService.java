package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Engineer;
import com.finalproject.engineerapp.model.House;
import com.finalproject.engineerapp.repositories.EngineerRepository;
import com.finalproject.engineerapp.repositories.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EngineerService {

    @Autowired
    private EngineerRepository engineerRepository;

    public List<Engineer> getEngineers() {
        List<Engineer> engineers = engineerRepository.findAll();
        return engineers;
    }

    public void updateEngineer(Engineer engineer) {
        engineerRepository.save(engineer);
    }

    public void addEngineer(Engineer engineer) {
        engineerRepository.save(engineer);
    }

    public void deleteEngineer(Engineer engineer) {
        engineerRepository.delete(engineer);
    }

    public Optional<Engineer> findEngineerById(Long id) {
        Optional<Engineer> engineer = engineerRepository.findById(id);
        return engineer;
    }
}
