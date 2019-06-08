package util.validator;

import entity.Address;


public class AddressValidator implements Validator<Address> {
    private static final String STRING_REGEX = "^[а-яА-ЯёЁa-zA-Z]+$";
    @Override
    public boolean validate(Address address) {
        boolean answer = false;
        if(address != null && validateString(address.getCity(),STRING_REGEX) && validateString(address.getCountry(),STRING_REGEX) &&
                validateString(address.getStreet(),STRING_REGEX)){
            answer = true;
        }
        return answer;
    }
}
