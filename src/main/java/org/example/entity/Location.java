package org.example.entity;

public class Location extends Entity {
    private int id;
    private String country;
    private String city;
    private String address;

    public Location(){}

    public Location(String country, String city, String address) {
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static Location getLocation(int id,String country,String city,String address){
        Location location=new Location();
        location.setId(id);
        location.setCountry(country);
        location.setCity(city);
        location.setAddress(address);
        return location;
    }
}
