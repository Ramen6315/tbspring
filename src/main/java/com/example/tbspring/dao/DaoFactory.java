package com.example.tbspring.dao;

public class DaoFactory {
    public UserDao userDao() {
        return new UserDao(createConnectionMaker());
    }

    public AccountDao accountDao() {
        return new AccountDao(createConnectionMaker());
    }

    public ConnectionMaker createConnectionMaker() {
        return new DConnectionMaker();
    }
}
