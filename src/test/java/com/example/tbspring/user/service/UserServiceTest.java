package com.example.tbspring.user.service;

import com.example.tbspring.Level;
import com.example.tbspring.MailSender;
import com.example.tbspring.TransactionHandler;
import com.example.tbspring.TxProxyFactoryBean;
import com.example.tbspring.domain.User;
import com.example.tbspring.user.repository.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
class UserServiceTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @Autowired
    UserDao userDao;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    MailSender mailSender;

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
        assertThat(userServiceImpl).isNotNull();
    }

    @Test
    void add() {
        //given
        User user = new User("newbie", "newbieName", "passwordnewBie", 0, 0, null);

        //when
        userServiceImpl.add(user);

        //then
        User newBieUser = userDao.get(user.getId());

        assertThat(newBieUser.getLevel()).isEqualTo(Level.BASIC);
    }

    @Test
    @DirtiesContext
    void updateLevels() {
        UserServiceImpl userService = new UserServiceImpl();
        MockUserDao mockUserDao = new MockUserDao(this.allUsers);
        userService.setUserDao(mockUserDao);

        MockMailSender mailSender = new MockMailSender();
        userServiceImpl.setMailSender(mailSender);

        userServiceImpl.updateLevels();

        List<User> users = mockUserDao.getAll();

        assertThat(users.size()).isEqualTo(2);

        checkLevel(users.get(0), Level.BASIC);
        checkLevel(users.get(1), Level.SILVER);
        checkLevel(users.get(2), Level.SILVER);
        checkLevel(users.get(3), Level.GOLD);
        checkLevel(users.get(4), Level.GOLD);
        checkLevel(users.get(5), Level.GOLD);

        List<String> request = mailSender.getRequests();
        assertThat(request.size()).isEqualTo(2);
        assertThat(request.get(0)).isEqualTo(users.get(1).getEmail());
        assertThat(request.get(1)).isEqualTo(users.get(3).getEmail());

    }

    @Test
    public void upgradeAllOrNothing() throws Exception {
        TestUserService testUserService = new TestUserService(allUsers.get(3).getId());
        testUserService.setUserDao(this.userDao);
        testUserService.setMailSender(mailSender);

        TransactionHandler txHandler = new TransactionHandler();
        txHandler.setTarget(testUserService);
        txHandler.setTransactionManager(platformTransactionManager);
        txHandler.setPattern("upgradeLevels");

        UserService txUserService =
                (UserService) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{UserService.class}, txHandler);

        ProxyFactoryBean txProxyFactoryBean = context.getBean("&userService", ProxyFactoryBean.class);
        txProxyFactoryBean.setTarget(testUserService);
        UserService userService = (UserService) txProxyFactoryBean.getObject();

        userDao.deleteAll();
        for(User user : allUsers) {
            userDao.add(user);
        }
        try{
            txUserService.updateLevels();
            fail("TestUserServiceException expected");
        } catch (Exception e) {
        }

    }

    private void checkLevel(User user1, Level level) {
        assertThat(user1.getLevel()).isEqualTo(level);
    }

    static class TestUserService extends UserServiceImpl {
        private String id;

        private TestUserService(String id) {
            this.id = id;
        }

        protected void upgradeLevel(User user) {
            if (user.getId().equals(this.id)) throw new TestUserServiceException();
            super.upgradeLevel(user);
        }
    }

    static class TestUserServiceException extends RuntimeException {
    }

    static class MockMailSender implements MailSender {
        private List<String> requests = new ArrayList<String>();

        public List<String> getRequests() {
            return requests;
        }

        public void send(SimpleMailMessage mailMessage) throws MailException {
            requests.add(mailMessage.getTo()[0]);
        }

        public void send(SimpleMailMessage[] mailMessage) throws MailException {
        }
    }

    static class MockUserDao implements UserDao {
        private List<User> users;
        private List<User> update = new ArrayList<>();

        public MockUserDao(List<User> users) {
            this.users = users;
        }

        public List<User> getUpdate() {
            return update;
        }

        @Override
        public void add(User user) {
            throw new UnsupportedOperationException();
        }

        @Override
        public User get(String id) {
            throw new UnsupportedOperationException();
        }

        @Override
        public List<User> getAll() {
            return users;
        }

        @Override
        public void deleteAll() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void update(User user) {
            update.add(user);
        }
    }
}