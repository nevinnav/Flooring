/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery.dao;

import com.nev.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.Collection;


public interface FlooringMasteryOrderDao {

// Allow user to get an order
// @param orderNumber
// @param orderDate
// @return object order associated with orderNumber and orderDate if it exists    
Order getOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryPersistenceException;

// Allow user to get all orders
// @param orderDate
// @return collection of orders with orderDate
Collection<Order> getAllOrders(LocalDate orderDate)  throws FlooringMasteryPersistenceException;

// Allow user to add order
// @param orderDate
// @param order object to be added
// @return order object associated with given associated order number if it exists
Order addOrder(Order order) throws FlooringMasteryPersistenceException;
    
// Allow user to edit the Customer Name, State, ProductType and Area of an Order
// If user does not enter data to edit, program will use existing information
// @param orderDate
// @param order object
// @return edited order object associated with given orderNumber and orderDate
Order editOrder(Order order) throws FlooringMasteryPersistenceException;

// Allow user to remove order
// @param orderDate
// @param orderNumber 
// @return remove order if orderNumber and order object has order date associated with order number.
// Display the information and prompt user if they are sure to remove it from list
Order removeOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryPersistenceException;

// Allow user to view all orders
// Method takes no param
// Writes all orders to screen for view
String exportAllOrders()  throws FlooringMasteryPersistenceException;

}
