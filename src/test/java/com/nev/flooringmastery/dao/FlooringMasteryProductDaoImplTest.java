/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery.dao;

import com.nev.flooringmastery.dto.Product;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class FlooringMasteryProductDaoImplTest {
    
    FlooringMasteryProductDao testProductDao;
    
    public FlooringMasteryProductDaoImplTest() {
    }
    
    @BeforeEach
    public void setUp() throws IOException {
        testProductDao = new FlooringMasteryProductDaoImpl("Test/testProducts.txt");
    }
    
    @Test
    public void testGetProduct() throws FlooringMasteryPersistenceException {
        //Set testProduct
        Product testProduct = testProductDao.getProduct("Hardwood");
        
        //Check specifics
        assertEquals(testProduct.getCostPerSqFt(), new BigDecimal(11.99).setScale(2, RoundingMode.HALF_UP), "Costs per square feet are the same.");
        assertEquals(testProduct.getLabourPerSqFt(), new BigDecimal(9.25).setScale(2, RoundingMode.HALF_UP), "Labour cost per square feet are the same.");
    }
    
    @Test
    public void testGetAllProducts() throws FlooringMasteryPersistenceException {
        //Get testAllProducts
        Collection<Product> testAllProducts = testProductDao.getAllProducts();
        
        //Check specifics
        assertNotNull(testAllProducts, "Product list should NOT be null.");
        assertEquals(5, testAllProducts.size(), "Product list should include 5 items.");
        assertFalse(testAllProducts.contains(testProductDao.getProduct("Brick")), "Product list should NOT contain brick.");
        assertTrue(testAllProducts.contains(testProductDao.getProduct("Vinyl")), "Product list should contain Vinyl.");
    }
}
