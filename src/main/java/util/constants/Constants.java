package util.constants;


public interface Constants {

    //AddressDAO
    String ID_COLUMN = "id";
    String COUNTRY_COLUMN = "country";
    String CITY_COLUMN = "city";
    String STREET_COLUMN = "street";
    String NUMBER_COLUMN = "numberOfHouse";
    String INSERT_ADDRESS = "INSERT INTO address(country,city,street,numberOfHouse) VALUES (?,?,?,?)";
    String UNIQUE_CHECK = "SELECT id FROM address WHERE country = ? AND city = ? AND street = ? AND numberOfHouse = ?";
    String UPDATE_ADDRESS = "UPDATE address SET country = ?, city = ?,street = ?, numberOfHouse = ? WHERE id = ?";
    String DELETE_ADDRESS = "DELETE FROM address WHERE id = ?";
    String GET_ADDRESS = "SELECT address.* FROM address inner JOIN user on address.id = user.addressID WHERE user.userID = ?";

    //GameDAO
    String GAME_ID_COLUMN = "id";
    String GAME_NAME_COLUMN = "name";
    String COAST_COLUMN = "cost";
    String RELEASE_DATE_COLUMN = "releaseDate";
    String DESCRIPTION_COLUMN = "description";
    String DEVELOPER_COLUMN = "developer";
    String MIN_SYS_REQ_COLUMN="min_system_req";
    String REC_SYS_REQ_COLUMN = "rec_system_req";
    String POSTER_LINK = "poster_link";
    String INSERT_GAME = "INSERT INTO game(name,cost,releaseDate,description,developer,min_system_req," +
            "rec_system_req,poster_link) VALUES (?,?,?,?,?,?,?,?)";
    String UPDATE_GAME = "UPDATE game SET name = ?, cost = ?, releaseDate = ?, description = ?, developer = ?, poster_link = ? WHERE id = ?";
    String GET_GAME = "SELECT * FROM game WHERE id = ?";
    String GET_ALL_GAMES = "SELECT * FROM game LIMIT ?,?";
    String DELETE_GAME = "DELETE FROM game WHERE  id = ?";
    String GET_USER_GAMES = "SELECT game.* FROM user_game INNER JOIN game ON user_game.gameID = game.id WHERE user_game.userID = ?";
    String DELETE_LINKS = "DELETE FROM user_game WHERE gameID = ?";
    String DELETE_USER_GAME = "DELETE FROM user_game WHERE userID = ? AND gameID = ?";
    String GAME_TO_USER = "INSERT INTO user_game(userID,gameID) VALUES (?,?)";

    //SystemRequirementsDAO
    String ID_SYS = "id";
    String OPERATION_SYS = "operationSystem";
    String CPU_NAME_SYS = "cpuName";
    String CPU_FREQ_SYS = "cpuFrequency";
    String RAM_SYS = "ram";
    String ADAPTER_NAME_SYS = "videoAdapterName";
    String ADAPTER_MEM_SYS = "videoAdaptermMemory";
    String FREE_SPACE_SYS = "freeSpace";
    String INSERT_SYS = "INSERT INTO system_requirements(operationSystem, cpuName, cpuFrequency," +
          " ram, videoAdapterName, videoAdaptermMemory, freeSpace) VALUES (?,?,?,?,?,?,?)";
    String UPDATE_SYS = "UPDATE system_requirements SET operationSystem = ?, cpuName = ?, cpuFrequency = ?, ram = ?, " +
            "videoAdapterName = ?, videoAdaptermMemory = ?, freeSpace = ? WHERE id = ?";
    String DELETE_SYS = "DELETE FROM system_requirements WHERE id = ?";
    String GET_SYS = "SELECT * FROM system_requirements WHERE id = ?";

    //UserAddressJoinDAO
    String SELECT_ALL_USERS_JOIN_ADDRESS = "SELECT * FROM user LEFT JOIN address ON user.addressID = address.id LIMIT ?,?";

    //UserDAO
    String USERID_COLUMN = "userID";
    String USERLOGIN_COLUMN = "userLogin";
    String USERPASSWORD_COLUMN = "userPassword";
    String DATE_OF_BIRTHDAY_COLUMN = "dateOfBirthday";
    String EMAIL_COLUMN = "email";
    String NAME_COLUMN = "name";
    String SNAME_COLUMN = "sname";
    String ADMIN_COLUMN = "admin";
    String ADDRESS_ID = "addressID";
    String INSERT_USER = "INSERT INTO user(userLogin,userPassword, dateOfBirthday,email,name,sname,addressID)" +
           " VALUES (?,?,?,?,?,?,?)";
    String DELETE_USER = "DELETE FROM user WHERE userLogin = ? AND userPassword = ?";
    String SELECT_BY_ID = "SELECT * FROM user WHERE userID = ?";
    String SELECT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE userLogin = ? AND userPassword = ?";
    String UPADTE_USER ="UPDATE IGNORE user SET name =? ,sname = ?, userPassword = ?, dateOfBirthday = ?," +
            "email = ? WHERE userID = ?";
    String GET_ALL = "SELECT * FROM user";

    //UserFriendDAO
    String ADD_FRIEND = "INSERT INTO user_user(userID, userFriendID) VALUES (?,?)";
    String CHECK_REL = "SELECT status FROM user_user WHERE userID = ?, userFriendID = ?";
    String GET_ALL_USER_FRIENDS = "SELECT user.userID,dateOfBirthday,email,user.name,sname " +
            "FROM user_user INNER JOIN user ON (user_user.userFriendID = user.userID OR user_user.userID = user.userID) " +
            "WHERE (user_user.userID = ? OR userFriendID = ?) AND  user_user.status = ? AND user.userID !=?";
    String DELETE_FRIEND = "DELETE FROM user_user WHERE (userID = ? AND userFriendID = ?) OR (userID = ? AND userFriendID = ?)";
    String CONFIRM_FRIEND = "UPDATE user_user SET status = 1 WHERE userID = ? AND userFriendID = ?";
    String DELETE_ALL_FRIENDS = "DELETE FROM user_user WHERE userID = ? OR userFriendID  = ?";


    //ActionConstants
    String USER_ATTRIBUTE = "user";
    String USERS_ATTRIBUTE = "users";
    String DELETTING_USER = "deletingUser";
    String DELETTING_ADDRESS = "deletingAddress";
    String GAMES_ATTRIBUTE = "games";
    String ADDED_GAME = "addedGame";
    String CURRENT_ACTION_ATTRIBUTE = "currentAction";
    String GET_USER_DIR = "/getUser";
    String LOGIN_DIR = "/mainPage/login";
    String PROFILE_DIR = "/mainPage/profile";
    String UPDATE_USER_DIR = "/updateUser";
    String MAIN_PAGE_DIR = "/mainPage";
    String MAIN_PAGE_JSP_DIR = "/MainPage.jsp";
    String LOGIN_JSP_DIR = "/Login.jsp";
    String PROFILE_JSPX_DIR = "/Profile.jspx";
    String CURRENT_PAGE = "currentPage";
    String ALL_USERS_JSPX_DIR = "/AllUsers.jspx";
    String DELERED_GAME = "deletedGame";
    String SHOW_GAMES = "/ShowGames.jspx";
    String GAMES_DIR = "/games";
    String USERS_DIR = "/users";
    String DELETE_USER_DIR = "/deleteUser";
    String REGISTER_NEW_USER_PAGE_DIR ="/register";
    String REGISTER_NEW_USER = "/registerUser";
    String DELETE_GAME_FROM_DATABASE = "/deleteGame";
    String MY_GAMES_DIR = "/mainPage/myGames";
    String ADD_NEW_GAME_DIR = "/mainPage/addNewGame";
    String ADD_PAGE_JSPX = "/AddNewGame.jspx";
    String ADD_POSTER_JSPX = "/AddNewGamePoster.jspx";
    String ADD_POSTER = "/addGameWithPoster";
    String ADD_GAME = "/addGame";
    String ADD_GAME_TO_USER = "/addGameToUser";
    String REGISTER_PAGE_JSPX = "/RegisterPage.jspx";
    String SHOW_MY_FRIENDS_DIR = "/mainPage/myFriends/friends";
    String SHOW_MY_REQUESTS = "/mainPage/myFriends/requests";
    String DELETE_FRIEND_DIR = "/deleteFriend";
    String USER_FRIENDS_JSPX = "/UserFriends.jspx";


    //Creator
    String LOGIN = "login";
    String PASSWORD  = "password";
    String OPERATION_SYS_MIN = "operationSystemMin";
    String CPU_NAME_SYS_MIN = "cpuNameMin";
    String CPU_FREQ_SYS_MIN = "cpuFrequencyMin";
    String RAM_SYS_MIN = "ramMin";
    String ADAPTER_NAME_SYS_MIN = "videoAdapterNameMin";
    String ADAPTER_MEM_SYS_MIN = "videoAdaptermMemoryMin";
    String FREE_SPACE_SYS_MIN = "freeSpaceMin";
    String OPERATION_SYS_REC = "operationSystemRec";
    String CPU_NAME_SYS_REC = "cpuNameRec";
    String CPU_FREQ_SYS_REC = "cpuFrequencyRec";
    String RAM_SYS_REC = "ramRec";
    String ADAPTER_NAME_SYS_REC = "videoAdapterNameRec";
    String ADAPTER_MEM_SYS_REC = "videoAdaptermMemoryRec";
    String FREE_SPACE_SYS_REC = "freeSpaceRec";

    //Uploader
    String PROPERTIES_NAME = "info";
    String PASSWORD_PROP = "password";
    String URL_PROP = "url";
    String POSTER_DIR = "posters_dir";
    String POSTERS_FOLDER = "/posters/";

    //Enum
    int ACTION_ID = 1;
    int ARCADE_ID = 2;
    int FIGHTING_ID = 4;
    int RPG_ID = 5;
    int SHOOTER_ID = 3;
}


