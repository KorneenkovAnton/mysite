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
        actions.put(UPDATE_USER_DIR,new UpdateUserAction());
        actions.put(MAIN_PAGE_DIR,new GetAllGamesAction());
        actions.put(GAMES_DIR, new GetAllGamesAction());
        actions.put(USERS_DIR,new ShowUsersAction());
        actions.put(DELETE_USER_DIR, new DeleteUserAction());
        actions.put(REGISTER_NEW_USER,new RegisterUserAction());
        actions.put(DELETE_GAME_FROM_DATABASE, new DeleteGameAction());
        actions.put(MY_GAMES_DIR,new ShowMyGamesAction());
        actions.put(ADD_GAME, new CreateNewGameAction());
        actions.put(ADD_POSTER,new AddNewGameAction());
        actions.put(ADD_GAME_TO_USER, new AddGameToUserAction());
        actions.put(SHOW_MY_FRIENDS_DIR, new MyFriendsAction());
        actions.put(DELETE_FRIEND_DIR,new DeleteFriendAction());
        actions.put(SHOW_MY_REQUESTS, new ShowMyFriendRequestsAction());
        actions.put(CONFIRM_FRIEND_ACTION,new ConfirmFriendAction());
        actions.put(ADD_FRIEND_ACTION, new AddFriendAction());
        actions.put(LANGUAGE_ACTION,new LanguageAction());
        actions.put(DONATE_ACTION, new DonateAction());
        actions.put(LOGOUT_ACTION,new LogoutAction());
        actions.put(SET_ADMIN_ACTION, new SetAdminAction());
        actions.put(SHOW_REGISTER_PAGE, new ShowRegisterPageAction());
        actions.put(SHOW_LOGIN_PAGE,new ShowLoginPageAction());
        actions.put(SHOW_ADD_NEW_GAME_PAGE,new ShowAddNewGamePageAction());
        actions.put(SHOW_PROFILE_PAGE, new ShowProfilePageAction());
        actions.put(SHOW_DONATE_PAGE, new ShowDonatePageAction());
        actions.put(SEARCH_GAME_ACTION, new SearchGameAction());
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
