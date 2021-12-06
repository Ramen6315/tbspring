package com.example.tbspring.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker {
    @Override
    public Connection makeNewConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "");
    }
}
