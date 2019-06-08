package util.validator;


import entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator implements Validator<User> {
    public static final String STRING_REGEX = "^[а-яА-ЯёЁa-zA-Z]+$";
    public static final String LOGIN_REGEX = "^[a-zA-Z][a-zA-Z0-9-]{1,20}$";
    public static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?!.*\\s).*$";
    public static final String EMAIL_REGEX = "([.[^@\\s]]+)@([.[^@\\s]]+)\\.([a-z]+)";

    public boolean validate(User user){
         boolean answer = false;
         if(user != null && validateString(user.getLogin(),LOGIN_REGEX) && validateString(user.getPassword(),PASSWORD_REGEX) &&
                 validateString(user.getName(), STRING_REGEX) && validateString(user.getsName(), STRING_REGEX) &&
                 validateString(user.geteMail(),EMAIL_REGEX)){
            answer = true;
         }
         return answer;
     }

}
