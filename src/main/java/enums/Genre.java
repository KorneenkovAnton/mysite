package enums;

import util.constants.Constants;

public enum  Genre implements Constants {
    ACTION(ACTION_ID),
    ARCADE(ARCADE_ID),
    SHOOTER(SHOOTER_ID),
    RPG(RPG_ID),
    FIGHTING(FIGHTING_ID);


    private int id;

    Genre(int actionId) {
        this.id = actionId;
    }

    public int getId(){
        return id;
    }
}
