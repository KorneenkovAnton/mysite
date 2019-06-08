package DAO;

import entity.Address;
import entity.User;
import util.constants.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AddressDAO implements DAO<Address, User>,Constants {



    public void addToDatabase(User user, Connection connection) throws SQLException {
        Address address = user.getAddress();
        PreparedStatement preparedStatement;
        if(isUnique(address,connection)){
            preparedStatement = connection.prepareStatement(INSERT_ADDRESS,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,address.getCountry());
            preparedStatement.setString(2,address.getCity());
            preparedStatement.setString(3,address.getStreet());
            preparedStatement.setInt(4,address.getNumberOfHouse());
            preparedStatement.executeUpdate();
            try(ResultSet generatedKey = preparedStatement.getGeneratedKeys()) {
                if(generatedKey.next()){
                    address.setId(generatedKey.getLong(1));
                    closePrepareStatement(preparedStatement);
                }
                else {
                    closePrepareStatement(preparedStatement);
                    throw new SQLException("Creating address failed");
                }
            }
        }
    }


    public void update(User user, Connection connection) throws SQLException {
        Address address = user.getAddress();
        if(isUnique(address,connection)) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADDRESS);
            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setInt(4,address.getNumberOfHouse());
            preparedStatement.setLong(5, address.getId());
            preparedStatement.executeUpdate();
            closePrepareStatement(preparedStatement);
        }
    }


    public void delete(User user, Connection connection) throws SQLException {
        Address address = user.getAddress();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADDRESS);
        preparedStatement.setLong(1,address.getId());
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }


    public Address getById(long id, Connection connection) throws SQLException {
        Address address = null;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ADDRESS);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            address = new Address();
            address.setId(resultSet.getLong(ID_COLUMN));
            address.setCountry(resultSet.getString(COUNTRY_COLUMN));
            address.setCity(resultSet.getString(CITY_COLUMN));
            address.setStreet(resultSet.getString(STREET_COLUMN));
            address.setNumberOfHouse(resultSet.getInt(NUMBER_COLUMN));
        }
        closePrepareStatement(preparedStatement);
        return address;
    }



    private boolean isUnique(Address address,Connection connection) throws SQLException {
        boolean unique = true;
        PreparedStatement preparedStatement = connection.prepareStatement(UNIQUE_CHECK);
        preparedStatement.setString(1,address.getCountry());
        preparedStatement.setString(2,address.getCity());
        preparedStatement.setString(3,address.getStreet());
        preparedStatement.setInt(4,address.getNumberOfHouse());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            address.setId(resultSet.getLong(ID_COLUMN));
            unique = false;
        }
        closePrepareStatement(preparedStatement);
        return unique;
    }
}
