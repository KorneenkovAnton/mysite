package site.actions;


public class AbstractActionFactory {
    public static ActionFactory instance;

    public static synchronized ActionFactory getInstance(){
        if(instance == null) {
            instance = new ActionFactory();
        }
        return instance;
    }
}
