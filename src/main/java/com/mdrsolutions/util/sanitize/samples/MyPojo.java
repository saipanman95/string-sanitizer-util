/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdrsolutions.util.sanitize.samples;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.mdrsolutions.util.sanitize.Sanitizable;
import com.mdrsolutions.util.sanitize.annotation.JsHtmlSanitizer;

/**
 *
 * @author michael.rodgers1
 */
@JsHtmlSanitizer
public class MyPojo implements Sanitizable{
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String comments;
    private MyChildPojo address;
    private List<MyChildPojo> addressList;
    private Map<String,MyChildPojo> addressMap;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public MyChildPojo getAddress() {
        return address;
    }

    public void setAddress(MyChildPojo address) {
        this.address = address;
    }

    public List<MyChildPojo> getAddressList() {
        return Collections.unmodifiableList(addressList);
    }

    public void setAddressList(List<MyChildPojo> addressList) {
        this.addressList = addressList;
    }

    public Map<String, MyChildPojo> getAddressMap() {
        return Collections.unmodifiableMap(addressMap);
    }

    public void setAddressMap(Map<String, MyChildPojo> addressMap) {
        this.addressMap = addressMap;
    }

    @Override
    public String toString() {
        return "MyPojo{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName + ", comments=" + comments + ", address=" + address + ", addressList=" + addressList + ", addressMap=" + addressMap + '}';
    }
    
    
    
}
