package com.example.tbspring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 상속을 통해 변경되는 부분을 다이나믹하게 만들어 줄 수 있지만 문제는 기능이 많아질수록 상속 또한 많아진다.
 */
//public class UserDaoDeleteAll extends UserDao{
//    @Override
//    protected PreparedStatement makeStatement(Connection connection) throws SQLException {
//        return connection.prepareStatement("delete form users");
//    }
//}
