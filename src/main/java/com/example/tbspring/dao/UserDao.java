package com.example.tbspring.dao;

import com.example.tbspring.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;

public class UserDao {
    private DataSource dataSource;
    private JdbcContext jdbcContext;

    public void setDataSource(DataSource dataSource) {
        this.jdbcContext = new JdbcContext();
        this.dataSource = dataSource;
        jdbcContext.setDataSource(dataSource);
    }

    public void add(final User user) throws ClassNotFoundException, SQLException {
        this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into user(id, name, password) values (?,?,?)"
                );
                preparedStatement.setString(1, user.getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getPassword());
                return preparedStatement;
            }
        }
        );
    }

//    public User get(String id) throws ClassNotFoundException, SQLException {
//        this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
//                return null;
//            }
//        });
//        Connection connection = dataSource.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id = ?");
//
//        preparedStatement.setString(1, id);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//        resultSet.next();
//
//        User user = new User();
//        user.setId(resultSet.getString("id"));
//        user.setName(resultSet.getString("name"));
//        user.setPassword(resultSet.getString("password"));
//
//        resultSet.close();
//        preparedStatement.close();
//        connection.close();
//
//        return user;
//    }
    public void deleteAll() throws SQLException {
        jdbcContext.excuteQuery("delete from users");
    }



    private void jdbcContextWithStatementStrategy(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = dataSource.getConnection();
            /**
             * 인터페이스 deleteAll()메서드에서 parameter로 받는 형식으로 DI를 구현 할 수 있다.
             */
            PreparedStatement preparedStatement = statementStrategy.makePreparedStatement(connection);
//            ps = makeStatement(connection);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public int getCount() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        return jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement("select count(*) from users");
            }
        }, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                return rs.getInt(1);
            }
        });

    }
}
