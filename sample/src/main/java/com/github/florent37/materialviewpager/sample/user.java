package com.github.florent37.materialviewpager.sample;

/**
 * Created by Fodil on 28/05/2017.
 */

public class user {
    private String id;

    private String token;

    private Double Longitude;

    private Double Latitude;

    public user(String token) {
        this.token = token;
    }

    public user() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }
}
