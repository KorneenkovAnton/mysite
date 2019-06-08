package DAO;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GameDAO<T,V> extends DAO<T,T> {
    void deleteLinks(T game, Connection connection) throws SQLException;
    List<T> getUserGames(V user, Connection connection) throws SQLException;
    void deleteAllUserGames(V user, Connection connection) throws  SQLException;
    void deleteUserGame(V user,long gameID, Connection connection) throws SQLException;
    List<T> getAll(int page,int recordsPerPage,Connection connection) throws SQLException;
    void addGameToUser(V user,T game, Connection connection) throws SQLException;
    List<T> getGameByName(int page, int recordsPerPage, String gameName, Connection connection) throws SQLException;
}

