package entity;


public class Address {
    private String country;
    private String city;
    private String street;
    private String numberOfHouse;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumberOfHouse() {
        return numberOfHouse;
    }

    public void setNumberOfHouse(String numberOfHouse) {
        this.numberOfHouse = numberOfHouse;
    }

    public Address(String country, String city, String street, String numberOfHouse) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.numberOfHouse = numberOfHouse;
    }

    public Address() {
    }
}
