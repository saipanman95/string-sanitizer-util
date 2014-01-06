package com.mdrsolutions.util.sanitize.samples;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
         if (null == context){
             System.out.println("context is null");
         }
        
        MyPojoSampleCase mpsc = (MyPojoSampleCase)context.getBean("myPojoSampleCase");
        MyPojoPopulator mpp = (MyPojoPopulator)context.getBean("myPojoPopulator");
        mpsc.save(mpp.populate());
    }
}
