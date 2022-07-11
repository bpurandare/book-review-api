package com.tcs.bookreview.dao;

import com.tcs.bookreview.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    @Query("select u from User u where username=:username")
    List<User> findUserByUsername(String username);
}
