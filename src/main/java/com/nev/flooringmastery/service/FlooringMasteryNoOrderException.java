/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery.service;


public class FlooringMasteryNoOrderException extends Exception {
    
    public FlooringMasteryNoOrderException(String message) {
        super(message);
    }
    
    public FlooringMasteryNoOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
