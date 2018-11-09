package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DAO <T,V> {
    void addToDatabase(V v, Connection connection) throws SQLException;
    void update(V v, Connection connection) throws SQLException;
    void delete(V V, Connection connection) throws SQLException;
    T getById(long id, Connection connection) throws SQLException;
    default void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
