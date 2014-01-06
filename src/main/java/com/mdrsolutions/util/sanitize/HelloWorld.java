/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdrsolutions.util.sanitize;

import org.springframework.stereotype.Component;

/**
 *
 * @author michael.rodgers1
 */
@Component
public class HelloWorld {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void printHello(){
    }
}
