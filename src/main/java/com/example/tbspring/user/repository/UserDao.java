package com.example.tbspring.user.repository;

import com.example.tbspring.domain.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

@Repository
public interface UserDao {
    void add(User user);
    User get(String id);
    List<User> getAll();
    void deleteAll();
    void update(User user);

}
