/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdrsolutions.util.sanitize.samples;

import com.mdrsolutions.util.sanitize.Sanitizable;
import com.mdrsolutions.util.sanitize.annotation.JsHtmlSanitizer;

/**
 *
 * @author michael.rodgers1
 */
@JsHtmlSanitizer
public class MyChildPojo implements Sanitizable{
    private int locationId;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "MyChildPojo{" + "locationId=" + locationId + ", streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + '}';
    }
    
    
    
}
