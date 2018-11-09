package DAO;

import entity.Address;
import entity.User;
import util.constants.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAddressJoinDAO implements Constants {

    public List<User> getAll(Connection connection,int page,int recordsPerPage) throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_JOIN_ADDRESS);
        preparedStatement.setLong(1,(page-1)*recordsPerPage);
        preparedStatement.setLong(2,recordsPerPage);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            users.add(new User(resultSet.getInt(USERID_COLUMN),resultSet.getDate(DATE_OF_BIRTHDAY_COLUMN),resultSet.getString(EMAIL_COLUMN),
                    resultSet.getString(NAME_COLUMN),resultSet.getString(SNAME_COLUMN),new Address(resultSet.getLong(ID_COLUMN),
                    resultSet.getString(COUNTRY_COLUMN),resultSet.getString(CITY_COLUMN),resultSet.getString(STREET_COLUMN),
                    resultSet.getInt(NUMBER_COLUMN))));
        }
        return users;
    }
}
