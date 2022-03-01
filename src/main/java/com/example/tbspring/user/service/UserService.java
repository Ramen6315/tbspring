package com.example.tbspring.user.service;

import com.example.tbspring.Level;
import com.example.tbspring.domain.User;
import com.example.tbspring.user.repository.UserDao;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {
    DataSource dataSource;
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void updateLevels() {
        try {
            Connection connection = dataSource.getConnection();
            List<User> allUsers = userDao.getAll();

            for(User user : allUsers) {
                if(user.canUpgradeLevels()) {
                    upgradeLevel(connection, user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void add(User user) {
        if(user.getLevel() == null) {
            user.setLevel(Level.BASIC);
        }
        userDao.add(user);
    }

    private void upgradeLevel(Connection connection, User user) {
        user.upgradeLevel();
        userDao.update(connection, user);
    }

}
