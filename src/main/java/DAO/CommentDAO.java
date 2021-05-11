package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CommentDAO<T,V> extends DAO<T,V>{
    List<T> getByGame(long gameId, Connection connection) throws SQLException;
}
