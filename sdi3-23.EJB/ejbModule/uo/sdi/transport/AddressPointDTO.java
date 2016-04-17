package uo.sdi.transport;

import java.io.Serializable;

import uo.sdi.model.types.AddressPoint;

public class AddressPointDTO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8105043244168605325L;
    
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private Double lat;
    private Double lon;

    public AddressPointDTO(){
    }

    public AddressPointDTO(AddressPoint addressPoint) {
        this.address = addressPoint.getAddress();
        this.city = addressPoint.getCity();
        this.state = addressPoint.getState();
        this.country = addressPoint.getCountry();
        this.zipCode = addressPoint.getZipCode();
        this.lat = addressPoint.getWaypoint().getLat();
        this.lon = addressPoint.getWaypoint().getLon();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
