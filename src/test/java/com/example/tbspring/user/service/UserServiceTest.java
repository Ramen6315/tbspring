package com.example.tbspring.user.service;

import com.example.tbspring.Level;
import com.example.tbspring.domain.User;
import com.example.tbspring.user.repository.UserDao;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    private List<User> allUsers;

    @BeforeEach
    void setUp() {
         allUsers = List.of(
                new User("user1", "name1", "password1", 49, 0, Level.BASIC),
                new User("user1", "name2", "password2", 50, 0, Level.BASIC),
                new User("user1", "name3", "password3", 60, 29, Level.SILVER),
                new User("user1", "name4", "password4", 60, 30, Level.SILVER),
                new User("user1", "name5", "password5", 100, 100, Level.GOLD),
                new User("user1", "name6", "password6", 87, 78, Level.GOLD)
        );
    }

    @Test
    void bean() {
        assertThat(userService).isNotNull();
    }

    @Test
    void add() {
        //given
        User user = new User("newbie", "newbieName", "passwordnewBie", 0, 0, null);

        //when
        userService.add(user);

        //then
        User newBieUser = userDao.get(user.getId());

        assertThat(newBieUser.getLevel()).isEqualTo(Level.BASIC);
    }

    @Test
    void updateLevels() {
        userDao.deleteAll();
        for(User user : allUsers) {
            userDao.add(user);
        }

        userService.updateLevels();
        List<User> users = userDao.getAll();
        checkLevel(users.get(0), Level.BASIC);
        checkLevel(users.get(1), Level.SILVER);
        checkLevel(users.get(2), Level.SILVER);
        checkLevel(users.get(3), Level.GOLD);
        checkLevel(users.get(4), Level.GOLD);
        checkLevel(users.get(5), Level.GOLD);

    }

    private void checkLevel(User user1, Level level) {
        assertThat(user1.getLevel()).isEqualTo(level);
    }
}