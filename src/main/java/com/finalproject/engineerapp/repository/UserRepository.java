package com.finalproject.engineerapp.repository;

import com.finalproject.engineerapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

//    @Query("SELECT u FROM User u WHERE u.username=:name")
//    User findByUsername(@Param("name") String username);

    User findByUsername(String username);
}
