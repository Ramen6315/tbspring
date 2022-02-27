package com.example.tbspring.dao;

import com.example.tbspring.Level;
import com.example.tbspring.domain.User;
import com.example.tbspring.user.repository.UserDaoJdbc;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserDaoJdbcTest {

    UserDaoJdbc userDao = new UserDaoJdbc();

    User user1;
    User user2;

    @BeforeEach
    void setUp() {
        user1 = new User("ramen", "jung", "password", 1, 2, Level.BASIC);
        user2 = new User("neng", "lee", "password2", 0, 1, Level.GOLD);
    }

    @Test
    void addAndGet() {
        //given
        userDao.add(user1);
        User user = userDao.get("ramen");

        userDao.add(user2);
        User user3 = userDao.get("neng");

        checkSameUser(user1, user);
        checkSameUser(user2, user3);
    }



    @Test
    void userUpdate() {
        userDao.deleteAll();
        userDao.add(user1);

        user1.setName("update Name");
        user1.setId("update Id");
        user1.setLevel(Level.GOLD);
        user1.setLogin(2);
        user1.setRecommend(3);

        userDao.update(user1);

        User userUpdate = userDao.get(user1.getId());

        checkSameUser(user1, userUpdate);

    }

    private void checkSameUser(User user1, User user) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(user1.getId()).isEqualTo(user.getId());
            softAssertions.assertThat(user1.getName()).isEqualTo(user.getName());
            softAssertions.assertThat(user1.getPassword()).isEqualTo(user.getPassword());
            softAssertions.assertThat(user1.getLogin()).isEqualTo(user.getLogin());
            softAssertions.assertThat(user1.getRecommend()).isEqualTo(user.getRecommend());
            softAssertions.assertThat(user1.getLevel()).isEqualTo(user.getLevel());
        });
    }
}