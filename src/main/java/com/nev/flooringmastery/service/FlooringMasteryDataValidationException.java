/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery.service;


public class FlooringMasteryDataValidationException extends Exception {
    
    public FlooringMasteryDataValidationException(String message) {
        super(message);
    }
    
    public FlooringMasteryDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
