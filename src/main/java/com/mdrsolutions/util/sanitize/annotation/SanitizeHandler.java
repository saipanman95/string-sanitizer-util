/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdrsolutions.util.sanitize.annotation;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 *
 * @author michael.rodgers1
 */
public interface SanitizeHandler {
//    public void stripBeans();    
    public Object foo(ProceedingJoinPoint joinPoint) throws Throwable;
}
