package entity;


public class Address {
    private long id;
    private String country;
    private String city;
    private String street;
    private int numberOfHouse;

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

    public int getNumberOfHouse() {
        return numberOfHouse;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNumberOfHouse(int numberOfHouse) {
        this.numberOfHouse = numberOfHouse;
    }

    public Address(long id, String country, String city, String street, int numberOfHouse) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.numberOfHouse = numberOfHouse;
    }

    public Address() {
    }
}
