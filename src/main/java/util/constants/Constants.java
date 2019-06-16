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

    //GameDAOImpl
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
    String DELETE_ALL_USER_GAMES = "DELETE FROM user_game WHERE userID = ?";
    String COUNT_OF_RECORDS_IN_GAME_TABLE = "SELECT COUNT(*) AS count FROM game";
    String COUNT_OF_FOUNDED_RECORDS_IN_GAME_TABLE = "SELECT COUNT(*) AS count FROM game WHERE name LIKE ?";
    String COUNT_OF_RECORDS_IN_USER_TABLE = "SELECT COUNT(*) AS count FROM user";
    String COUNT = "count";
    String SEARCH_GAME = "SELECT * FROM game WHERE name LIKE ? LIMIT ?,?";
    String SEARCH_GAME_NAME = "searchGame";

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


    //UserDAOImpl
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
    String SELECT_BY_ID = "SELECT * FROM user WHERE userID = ?";
    String SELECT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE userLogin = ? AND userPassword = ?";
    String UPADTE_USER ="UPDATE IGNORE user SET name =? ,sname = ?, userPassword = ?, dateOfBirthday = ?," +
            "email = ? WHERE userID = ?";
    String GET_ALL = "SELECT * FROM user WHERE userID != ? LIMIT ?,?";
    String DONATE = "UPDATE user SET money = money + ? WHERE userID = ?";
    String MONEY_COLUMN = "money";
    String BUY_GAME = "UPDATE user SET money = money - (SELECT game.cost FROM game WHERE id = ?) WHERE userID = ?";
    String UPDATE_ADMIN_ROLE = "UPDATE user SET admin = 1 WHERE userID = ?";

    //UserFriendDAOImpl
    String ADD_FRIEND = "INSERT INTO user_user(userID, userFriendID) VALUES (?,?)";
    String CHECK_REL = "SELECT status FROM user_user WHERE (userID = ? AND userFriendID = ?) OR (userID = ? AND userFriendID = ?)";
    String GET_ALL_USER_FRIENDS = "SELECT user.userID,dateOfBirthday,email,user.name,sname " +
            "FROM user_user INNER JOIN user ON (user_user.userFriendID = user.userID OR user_user.userID = user.userID) " +
            "WHERE (user_user.userID = ? OR userFriendID = ?) AND  user_user.status = 1 AND user.userID !=?";
    String GET_ALL_USER_FRIEND_REQUESTS = "SELECT user.userID,dateOfBirthday,email,user.name,sname " +
            "FROM user_user INNER JOIN user ON (user_user.userID = user.userID) " +
            "WHERE (userFriendID = ?) AND  user_user.status = 0 AND user.userID !=?";;
    String DELETE_FRIEND = "DELETE FROM user_user WHERE (userID = ? AND userFriendID = ?) OR (userID = ? AND userFriendID = ?)";
    String CONFIRM_FRIEND = "UPDATE user_user SET status = 1 WHERE (userID = ? AND userFriendID = ?)";
    String DELETE_ALL_FRIENDS = "DELETE FROM user_user WHERE userID = ? OR userFriendID  = ?";
    String DELETE_USER_BY_ID = "DELETE FROM user WHERE user.userID = ?";


    //ActionConstants
    String USER_ATTRIBUTE = "user";
    String USERS_ATTRIBUTE = "users";
    String DELETED_USER = "deletingUser";
    String DELETED_ADDRESS = "deletingAddress";
    String GAMES_ATTRIBUTE = "games";
    String COUNT_OF_PAGES_ATTRIBUTE = "countOfPages";
    String ADDED_GAME = "addedGame";
    String NEW_ADMIN_ID = "newAdminId";
    String OPERATION_STATUS = "operationStatus";
    String LANGUAGE = "lan";
    String CART_ATTRIBUTE = "cart";
    String GET_USER_ACTION = "/getUser";
    String UPDATE_USER_ACTION = "/updateUser";
    String MAIN_PAGE_ACTION = "/mainPage";
    String LOGIN_JSP = "/WEB-INF/pages/Login.jsp";
    String CURRENT_PAGE = "currentPage";
    String ALL_USERS_PAGE_JSP = "/WEB-INF/pages/AllUsers.jsp";
    String DELETED_GAME = "deletedGame";
    String SHOW_GAMES_JSP = "/WEB-INF/pages/ShowGames.jsp";
    String USERS_DIR = "/users";
    String DELETE_USER_ACTION = "/deleteUser";
    String REGISTER_NEW_USER_ACTION = "/registerUser";
    String DELETE_GAME_FROM_DATABASE_ACTION = "/deleteGame";
    String MY_GAMES_ACTION = "/mainPage/myGames";
    String ADD_GAME_PAGE_JSP = "/WEB-INF/pages/AddNewGame.jsp";
    String ADD_POSTER_JSP = "/WEB-INF/pages/AddNewGamePoster.jsp";
    String ADD_POSTER_ACTION = "/addGameWithPoster";
    String ADD_GAME_ACTION = "/addGame";
    String ADD_GAME_TO_USER_ACTION = "/addGameToUser";
    String REGISTER_PAGE_JSP = "/WEB-INF/pages/RegisterPage.jsp";
    String SHOW_MY_FRIENDS_ACTION = "/mainPage/myFriends/friends";
    String SHOW_MY_REQUESTS_ACTION = "/mainPage/myFriends/requests";
    String DELETE_FRIEND_ACTION = "/deleteFriend";
    String USER_FRIENDS_JSP = "/WEB-INF/pages/UserFriends.jsp";
    String PROFILE_JSP = "/WEB-INF/pages/Profile.jsp";
    String DONATE_JSP = "/WEB-INF/pages/Donate.jsp";
    String CART_JSP = "/WEB-INF/pages/Cart.jsp";
    String SHOW_SEARCH_GAME_JSP = "/WEB-INF/pages/ShowSearchGame.jsp";
    String FRIEND_ID = "friendID";
    String STATUS = "status";
    String ADD_FRIEND_ID = "addFriendID";
    String CONFIRM_FRIEND_ACTION = "/confirmFriend";
    String ADD_FRIEND_ACTION = "/addFriend";
    String LANGUAGE_ACTION = "/lan";
    String DONATE_ACTION = "/donate";
    String SHOW_MY_GAMES_PAGE_JSP = "/WEB-INF/pages/ShowMyGames.jsp";
    String LOGOUT_ACTION = "/logout";
    String SET_ADMIN_ACTION = "/setAdmin";
    String SHOW_REGISTER_PAGE = "/register";
    String SHOW_LOGIN_PAGE = "/login";
    String SHOW_ADD_NEW_GAME_PAGE = "/addNewGame";
    String SHOW_PROFILE_PAGE = "/profile";
    String SHOW_DONATE_PAGE = "/donatePage";
    String SEARCH_GAME_ACTION = "/searchGame";
    String ADD_GAME_TO_CART = "/addGameToCart";
    String SHOW_CART_PAGE = "/cart";
    String DELETE_FROM_CART = "/deleteFromCart";
    String BUY_FROM_CART_ACTION = "/buyGames";

    //OperationStatus
    String OPERATION_SUCCESS = "Success";
    String OPERATION_ERROR = "Error";
    String OPERATION_WRONG_ID = "Unexpected friend id";
    String NOT_ENOUGH_MONEY = "Not enough money";
    String GAME_VALIDATION_ERROR = "Validation error";
    String GAME_AVAILABLE = "The game is already available";


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
    String DONATE_ATTRIBUTE = "donate";

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


