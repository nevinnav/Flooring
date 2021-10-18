/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery.service;

import com.nev.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.nev.flooringmastery.dto.Order;
import com.nev.flooringmastery.dto.Product;
import com.nev.flooringmastery.dto.StateTax;
import java.time.LocalDate;
import java.util.Collection;


public interface FlooringMasteryServiceLayer {
    
    Order processNewOrder(Order order) throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException;

    
    Order processEditOrder(Order order) throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException;
            
            
    Order getOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderException;


    Collection<Order> getAllOrders(LocalDate orderDate)  throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderException;


    Order removeOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderException;


    String exportAllOrders()  throws FlooringMasteryPersistenceException;
    

    Product getProduct(String productType) throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException;
    

    Collection<Product> getAllProducts() throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException;
    

    StateTax getStateTax(String stateAbbreviation) throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException;
    

    Collection<StateTax> getAllStateTax() throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException;
}
