package com.example.tbspring.dao;

import org.springframework.jdbc.core.ConnectionCallback;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatementStrategy(StatementStrategy statementStrategy) throws SQLException {
        Connection connection =  null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.dataSource.getConnection();

            preparedStatement = statementStrategy.makePreparedStatement(connection);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
