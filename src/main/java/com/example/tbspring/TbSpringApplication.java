package com.example.tbspring;

import com.example.tbspring.dao.AccountDao;
import com.example.tbspring.dao.ConnectionMaker;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class TbSpringApplication {
    public static void main(String[] args) throws SQLException {
        AccountDao accountDao = new AccountDao();
        ConnectionMaker connectionMaker = new ConnectionMaker() {
            @Override
            public Connection makeNewConnection() {
                return null;
            }
        };
        accountDao.setUp(connectionMaker);

    }
}