/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery.dao;

import com.nev.flooringmastery.dto.Product;
import java.util.Collection;


public interface FlooringMasteryProductDao {
    
    // Allow user to get product from product list
    // @param productType
    // @return product object (product, cost, labor)
    Product getProduct(String productType) throws FlooringMasteryPersistenceException;
    
    // Display to user list of all products
    // Method takes no param
    // @returns collection of products
    Collection<Product> getAllProducts() throws FlooringMasteryPersistenceException;
}
