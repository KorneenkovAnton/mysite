package util.validator;

import entity.Game;


public class GameValidator implements Validator<Game> {
    private static final String STRING_REGEX = "^[а-яА-ЯёЁa-zA-Z  '-.:?0-9]+$";

    @Override
    public boolean isValid(Game game) {
        System.out.println(game);
        boolean answer = false;
        if (game != null && validateString(game.getName(), STRING_REGEX) && validateString(game.getDeveloper(), STRING_REGEX)) {
            answer = true;
        }
        return answer;
    }

}
