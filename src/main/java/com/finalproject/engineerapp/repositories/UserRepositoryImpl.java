package com.finalproject.engineerapp.repositories;

import com.finalproject.engineerapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class UserRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public User findByUsername(String username) {
        TypedQuery<User> theQuery = entityManager.createQuery("SELECT u FROM User u WHERE u.username=:username", User.class);
        theQuery.setParameter("username", username);
        try {
            return theQuery.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            // Handle the case when no user with the given username is found.
            return null;
        }
    }
}
