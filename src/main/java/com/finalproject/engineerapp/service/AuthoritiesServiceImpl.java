package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Authority;
import com.finalproject.engineerapp.repository.AuthoritiesRepository;
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
    public List<Authority> findAll() {
        List<Authority> authorities = authoritiesRepository.findAll();
        return authorities;
    }

    @Override
    public void save(Authority authority) {
        authoritiesRepository.save(authority);
    }

    @Override
    public void delete(Authority authority) {
        authoritiesRepository.delete(authority);
    }

    @Override
    public Optional<Authority> findById(Long id) {
        Optional<Authority> authorities = authoritiesRepository.findById(id);
        return authorities;
    }
}
