package DAO;


import entity.User;
import util.constants.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFriendDAO implements  Constants {


    public void addFriendToDataBase(User user, long friendID, Connection connection) throws SQLException {
        if(!isFriends(user.getId(),friendID,connection)) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_FRIEND);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, friendID);
            preparedStatement.execute();
        }
    }


    public List<User> selectAllFriendsOfUser(User user, Connection connection,int status) throws SQLException {
        List<User> friends = null;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USER_FRIENDS);
        preparedStatement.setLong(1,user.getId());
        preparedStatement.setLong(2,user.getId());
        preparedStatement.setLong(3,status);
        preparedStatement.setLong(4,user.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            friends = new ArrayList<>();
            do{
                friends.add(new User(resultSet.getLong(USERID_COLUMN),resultSet.getDate(DATE_OF_BIRTHDAY_COLUMN),
                        resultSet.getString(EMAIL_COLUMN),resultSet.getString(NAME_COLUMN),resultSet.getString(SNAME_COLUMN)));
            }while (resultSet.next());
        }
        return friends;
    }


    public void deleteAllFriendsOfUser(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_FRIENDS);
        preparedStatement.setLong(1,user.getId());
        preparedStatement.setLong(2,user.getId());
        preparedStatement.executeUpdate();
    }


    public void deleteFriend(User user,long friendId,Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FRIEND);
        preparedStatement.setLong(1,user.getId());
        preparedStatement.setLong(2,friendId);
        preparedStatement.setLong(3,friendId);
        preparedStatement.setLong(4,user.getId());
        preparedStatement.executeUpdate();
    }


    public void confirmRel(User user, long friendID, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CONFIRM_FRIEND);
        preparedStatement.setLong(1,user.getId());
        preparedStatement.setLong(2,friendID);
        preparedStatement.executeUpdate();
    }


    private boolean isFriends(long userID,long friendID,Connection connection) throws SQLException {
        boolean isFriend = false;
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_REL);
        preparedStatement.setLong(1,friendID);
        preparedStatement.setLong(2,userID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            isFriend = true;
        }
        return isFriend;
    }
}
