package com.example.tbspring.user.service;

import com.example.tbspring.Level;
import com.example.tbspring.domain.User;
import com.example.tbspring.user.repository.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void updateLevels() {
        List<User> allUsers = userDao.getAll();

        for(User user : allUsers) {
            upgradeUser(user);
        }
    }

    public void add(User user) {
        if(user.getLevel() == null) {
            user.setLevel(Level.BASIC);
        }
        userDao.add(user);
    }

    private void upgradeUser(User user) {
        if(user.canUpgradeSilver()) {
            user.setLevel(Level.SILVER);
        } else if(user.canUpgradeGold()){
            user.setLevel(Level.GOLD);
        }
        userDao.update(user);
    }

}
