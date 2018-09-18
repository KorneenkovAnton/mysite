package DAO;


import entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DAO <T> {
    void addToDatabase(T t, Connection connection) throws SQLException;
    void update(T t, Connection connection) throws SQLException;
    void delete(T t, Connection connection) throws SQLException;
    T getById(int id, Connection connection) throws SQLException;
    T getByLoginAndPassword(String login, String password, Connection connection) throws SQLException;
    List<T> getAll(User user,Connection connection);
}
