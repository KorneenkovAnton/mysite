package site.actions;

/**
 * Created by Антон on 07.10.2018.
 */
public class AbstractActionFactory {
    public final static  ActionFactory instance = new ActionFactory();

    public static  ActionFactory getInstance(){
        return instance;
    }
}
