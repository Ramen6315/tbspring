package com.example.tbspring.dao;

import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

public class AccountDao {
    private ConnectionMaker connectionMaker;

    public void setUp(ConnectionMaker connectionMaker) {
        try{
            this.connectionMaker = connectionMaker;
            connectionMaker.makeNewConnection();

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
