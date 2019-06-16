package site.actions;


public class AbstractActionFactory {
    public final static  ActionFactory instance = new ActionFactory();

    public static  ActionFactory getInstance(){
        return instance;
    }
}
