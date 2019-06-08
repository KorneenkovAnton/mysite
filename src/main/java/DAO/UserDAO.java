package DAO;

import entity.Game;
import entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public interface UserDAO<T,V> extends DAO<T,V> {
    List<T> getAll(T user,int page, int recordsPerPage, Connection connection) throws SQLException;
    T getByLoginAndPassword(String login, String password , Connection connection) throws SQLException;
    void donate(T user, int money, Connection connection) throws SQLException;
    void byGame(T user , Game game, Connection connection)throws SQLException;
    void setAdmin(T user, Connection connection) throws SQLException;
}
