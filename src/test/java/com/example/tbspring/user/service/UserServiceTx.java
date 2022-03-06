package com.example.tbspring.user.service;

import com.example.tbspring.domain.User;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class UserServiceTx implements UserService {
    PlatformTransactionManager platformTransactionManager;
    UserServiceImpl userService;

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
        this.platformTransactionManager = platformTransactionManager;
    }

    @Override
    public void updateLevels() throws Exception {
        TransactionStatus transaction = this.platformTransactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            userService.updateLevels();

            this.platformTransactionManager.commit(transaction);
        } catch (Exception e) {
            this.platformTransactionManager.rollback(transaction);
            e.printStackTrace();
        }

    }

    @Override
    public void add(User user) {
        userService.add(user);
    }
}
