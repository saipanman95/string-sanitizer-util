/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdrsolutions.util.sanitize.annotation.impl;

import com.mdrsolutions.util.sanitize.annotation.SanitizeHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 *
 * @author michael.rodgers1
 */
@Aspect
@Component
public class SanitizeHandlerImpl implements SanitizeHandler{

    @Around("@annotation(mil.army.hrc.util.sanitize.annotation.JsHtmlSanitizer)")
    @Override
    public Object foo(ProceedingJoinPoint joinPoint) throws Throwable {
        
        Object[] args = joinPoint.getArgs();
        for (Object obj : args){
            SanitizeAnnotationParser.stripParser(obj);
        }
        return joinPoint.proceed();
    }
    
}
