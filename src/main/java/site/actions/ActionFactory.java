package site.actions;

import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory implements Constants {
    private Map<String,Action> actions = new HashMap<>();
    private Action action;

    public ActionFactory() {
        actions.put(GET_USER_DIR, new GetUserAction());
        actions.put(LOGIN_DIR , new ShowLoginPageAction());
        actions.put(PROFILE_DIR, new ShowProfilePageAction());
        actions.put(UPDATE_USER_DIR,new UpdateUserAction());
        actions.put(MAIN_PAGE_DIR,new GetAllGamesAction());
        actions.put(GAMES_DIR, new GetAllGamesAction());
        actions.put(USERS_DIR,new ShowUsersAction());
        actions.put(DELETE_USER_DIR, new DeleteUserAction());
        actions.put(REGISTER_NEW_USER_PAGE_DIR, new ShowRegisterPageAction());
        actions.put(REGISTER_NEW_USER,new RegisterUserAction());
        actions.put(DELETE_GAME_FROM_DATABASE, new DeleteGameAction());
        actions.put(MY_GAMES_DIR,new ShowMyGamesPageAction());
        actions.put(ADD_GAME, new CreateNewGameAction());
        actions.put(ADD_NEW_GAME_DIR, new ShowAddGamePageAction());
        actions.put(ADD_POSTER,new AddNewGameAction());
        actions.put(ADD_GAME_TO_USER, new AddGameToUSerAction());
        actions.put(SHOW_MY_FRIENDS_DIR, new MyFriendsAction());
        actions.put(DELETE_FRIEND_DIR,new DeleteFriendAction());
        actions.put(SHOW_MY_REQUESTS, new MyFriendsAction());
        actions.put("/confirmFriend",new ConfirmFriendAction());
    }

    public synchronized Action getAction(HttpServletRequest request, HttpServletResponse response){
        String actionKey = request.getServletPath();
        action = actions.get(actionKey);
        if(action == null){
            try {
                response.getWriter().write("wrong url");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Wrong Action");
        }
        return action;
    }
}
