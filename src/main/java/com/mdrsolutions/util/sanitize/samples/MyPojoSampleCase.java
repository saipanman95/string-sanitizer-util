/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdrsolutions.util.sanitize.samples;

import java.util.Date;
import com.mdrsolutions.util.sanitize.annotation.JsHtmlSanitizer;
import com.mdrsolutions.util.sanitize.annotation.impl.SanitizeAnnotationParser;
import org.springframework.stereotype.Component;

/**
 *
 * @author michael.rodgers1
 */
@Component
public class MyPojoSampleCase {
       
    @JsHtmlSanitizer
    public void save(MyPojo myPojo){
        System.out.println("myPojo+ " + myPojo.toString());
    }
    
    public void saveWithEscape(MyPojo myPojo){
        Date start = new Date();
        System.out.println("start time = " + start.toString());
        SanitizeAnnotationParser.stripParser(myPojo);
        Date end = new Date();
        System.out.println("end time = " + end.toString()); 
        
        System.out.println("myPojo after escaping " + myPojo.toString());
    }

}
