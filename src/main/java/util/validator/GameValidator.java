package util.validator;

import entity.Game;




public class GameValidator implements Validator<Game> {
    private static final  String STRING_REGEX = "^[a-zA-Z0-9]+$";
    @Override
    public boolean validate(Game game) {
        boolean answer = false;
        if(game != null && validateString(game.getName(), STRING_REGEX) && validateString(game.getDeveloper(), STRING_REGEX) &&
                validateString(game.getDescription(), STRING_REGEX)){
            answer = true;
        }
        return answer;
    }

}
