/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery.dao;


public interface FlooringMasteryAuditDao {
 
    public void writeAuditEntry(String entry) throws FlooringMasteryPersistenceException;
    
}
