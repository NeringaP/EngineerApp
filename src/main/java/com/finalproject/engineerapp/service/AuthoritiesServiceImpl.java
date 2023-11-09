package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Authorities;
import com.finalproject.engineerapp.repositories.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {


    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    public AuthoritiesServiceImpl(AuthoritiesRepository authoritiesRepository) {
        this.authoritiesRepository = authoritiesRepository;
    }

    @Override
    public List<Authorities> findAll() {
        List<Authorities> authorities = authoritiesRepository.findAll();
        return authorities;
    }

    @Override
    public void save(Authorities authorities) {
        authoritiesRepository.save(authorities);
    }

    @Override
    public void delete(Authorities authorities) {
        authoritiesRepository.delete(authorities);
    }

    @Override
    public Optional<Authorities> findById(Long id) {
        Optional<Authorities> authorities = authoritiesRepository.findById(id);
        return authorities;
    }
}
