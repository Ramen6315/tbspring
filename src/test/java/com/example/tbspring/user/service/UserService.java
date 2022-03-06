package com.example.tbspring.user.service;

import com.example.tbspring.domain.User;

public interface UserService {
    public void updateLevels() throws Exception;
    public void add(User user);
}
