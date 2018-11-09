package util.creator;

import entity.Address;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 07.10.2018.
 */
public class AddressCreator implements Creator<Address>,Constants {
    @Override
    public Address create(HttpServletRequest request) {
        Address address = new Address();
        address.setCountry(request.getParameter(COUNTRY_COLUMN));
        address.setCity(request.getParameter(CITY_COLUMN));
        address.setStreet(request.getParameter(STREET_COLUMN));
        address.setNumberOfHouse(Integer.parseInt(request.getParameter(NUMBER_COLUMN)));
        return address;
    }
}
