/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdrsolutions.util.sanitize;

/**
 *
 * @author michael.rodgers1
 */
public interface Sanitizable {
    /**
     * Sanitizable interface must be on Entity type objects that need to be
     * scanned with the Escape annotation. Implementing the Sanitizable interfaces
     * makes it easier to identify where to scan and not using reflection.
     * 
     * Basically, DO NOT REMOVE implements Sanitizable from objects -- thanks!
     */
}
