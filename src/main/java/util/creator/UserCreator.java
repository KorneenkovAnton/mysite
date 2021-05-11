package util.creator;

import entity.Address;
import entity.User;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class UserCreator implements Creator<User>, Constants {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public User create(HttpServletRequest request) {
        User user = new User();
        Address address = new Address();

        try {
            user.setId(Long.parseLong(request.getParameter(ID_COLUMN)));
        }catch (NumberFormatException e){
            System.out.println("new user");
        }

        user.setName(request.getParameter(NAME_COLUMN));
        user.setsName(request.getParameter(SNAME_COLUMN));
        user.setLogin(request.getParameter(LOGIN));
        user.setPassword(request.getParameter(PASSWORD));
        try {
            user.setDateOfBirthday(simpleDateFormat.parse(request.getParameter(DATE_OF_BIRTHDAY_COLUMN)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.seteMail(request.getParameter(EMAIL_COLUMN));

        try {
            address.setId(Long.parseLong(request.getParameter("address_id")));
        }catch (NumberFormatException e){
            System.out.println("new address");
        }

        address.setCountry(request.getParameter(COUNTRY_COLUMN));
        address.setCity(request.getParameter(CITY_COLUMN));
        address.setStreet(request.getParameter(STREET_COLUMN));
        address.setNumberOfHouse(Integer.parseInt(request.getParameter(NUMBER_COLUMN)));
        user.setAddress(address);

        return user;
    }
}
