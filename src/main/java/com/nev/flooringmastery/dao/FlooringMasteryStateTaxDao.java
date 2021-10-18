/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery.dao;

import com.nev.flooringmastery.dto.StateTax;
import java.util.Collection;


public interface FlooringMasteryStateTaxDao {
    
    // Allow user to get StateTax object 
    // @Param state abbreviation
    // @Return object StateTax (state and tax rate)
    StateTax getStateTax(String state) throws FlooringMasteryPersistenceException;
    
    // Allow user to get list of all available states and tax rates
    // Method requires no param
    // @return list of states and associated tax rates
    Collection<StateTax> getAllStateTax() throws FlooringMasteryPersistenceException;
}
