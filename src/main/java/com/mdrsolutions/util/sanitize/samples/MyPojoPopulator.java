/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdrsolutions.util.sanitize.samples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author michael.rodgers1
 */
@Component
public class MyPojoPopulator {

    private MyPojo myPojo;
    private MyChildPojo address;

    public MyPojo populate() {
        myPojo = new MyPojo();
        myPojo.setId(1);
        myPojo.setLastName("Butler");
        myPojo.setFirstName("Gerrard");
        myPojo.setMiddleName("Yeah");
        myPojo.setComments("Typing-some CPT Mateo’s potential ranks number 4 of the 26 Company Commanders I currently Senior Rate, and in the top 5%  <script>alert('test'); alert(); go();</script>alert(); information \"for\" “TESTING” testing… — purposes •  o            <input type='button' onclick='alert();'/>");
        address = new MyChildPojo();
        address.setStreetAddress("2109 <script> alert('test');<script> Vera Cruz Way");
        address.setCity("Vera Cruz <input type='input' onblur='alert('gotcha');' value='gotcha'/> alert");
        address.setState("TX");
        address.setZipCode("43543");
        myPojo.setAddress(address);
        myPojo.setAddressList(popMyChildPojoList());
        myPojo.setAddressMap(popMyChildPojoMap());

        return myPojo;
    }

    private List<MyChildPojo> popMyChildPojoList() {
        List<MyChildPojo> childPojos = new ArrayList<MyChildPojo>();

        for (int i = 0; i < 4; i++) {
            MyChildPojo adrss = new MyChildPojo();
            adrss.setStreetAddress(i + "2109 <script> alert('test');<script> Vera Cruz Way");
            adrss.setCity(i + "Vera Cruz ‘TESTING’ <input type='input' onblur='alert('gotcha" + i + "');' value='gotcha'/>");
            adrss.setState("TX");
            adrss.setZipCode("4354" + i);
            childPojos.add(adrss);
        }
        return childPojos;
    }
    
        private Map<String, MyChildPojo> popMyChildPojoMap() {
        Map<String, MyChildPojo> childPojos = new HashMap<String, MyChildPojo>();

        for (int i = 0; i < 10; i++) {
            MyChildPojo adrss = new MyChildPojo();
            adrss.setStreetAddress(i + "2109 <script>ÕÕÕÕÕÕÕÕÕÕÕÕÕÕÕÕ function go(txt){document.getElementById('body').value = 'YEAH got cha!' + txt;}<script> Vera Cruz Way");
            adrss.setCity(i + "Vera Cruz <input type='input' onblur='go('gotcha" + i + "');' value='gotcha'/>");
            adrss.setState("TX");
            adrss.setZipCode("4354" + i);
            String index = i + "_in";
            childPojos.put(index, adrss);
        }
        return childPojos;
    }
}
