/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery.dao;

import com.nev.flooringmastery.dto.Order;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class FlooringMasteryOrderDaoImplTest {
    
    FlooringMasteryOrderDao testOrderDao;
    
    public FlooringMasteryOrderDaoImplTest() {
        
    }
    
    @BeforeEach
    public void setUp() {
        testOrderDao = new FlooringMasteryOrderDaoImpl("Test/Orders_");

    }
    
    @Test
    public void testAddAndGetOrder() throws Exception {

    //Create test inputs
    Order testOrder = new Order();

    testOrder.setOrderNumber(1);
    testOrder.setCustomerName("Stephen Curry");
    testOrder.setStateAbbreviation("CA");
    testOrder.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
    testOrder.setProductType("Hardwood");
    testOrder.setArea(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
    testOrder.setCostPerSqFt(new BigDecimal("1.00").setScale(2, RoundingMode.HALF_UP));
    testOrder.setLabourPerSqFt(new BigDecimal("1.00").setScale(2, RoundingMode.HALF_UP));
    testOrder.setMaterialCost();
    testOrder.setLabourCost();
    testOrder.setTaxCost();
    testOrder.setTotalCost();
    testOrder.setOrderDate(LocalDate.now());
    
    //Add testOrder to the testDao
    testOrderDao.addOrder(testOrder);
    //Gett order from the DAO
    Order retrievedOrder = testOrderDao.getOrder(testOrder.getOrderDate(), testOrder.getOrderNumber());
    
    //Check the data is equal
    assertEquals(testOrder.getOrderDate(), retrievedOrder.getOrderDate(), "Checking order date.");
    assertEquals(testOrder.getCustomerName(), retrievedOrder.getCustomerName(), "Checking customer name.");
    assertEquals(testOrder.getStateAbbreviation(), retrievedOrder.getStateAbbreviation(), "Checking state.");
    assertEquals(testOrder.getTaxRate(), retrievedOrder.getTaxRate(), "Checking tax rate.");
    assertEquals(testOrder.getProductType(), retrievedOrder.getProductType(), "Checking product type.");
    assertEquals(testOrder.getArea(), retrievedOrder.getArea(), "Checking area.");
    assertEquals(testOrder.getCostPerSqFt(), retrievedOrder.getCostPerSqFt(), "Checking product costs per sq. ft.");
    assertEquals(testOrder.getLabourPerSqFt(), retrievedOrder.getLabourPerSqFt(), "Checking labour costs per sq. ft.");
    assertEquals(testOrder.getMaterialCost(), retrievedOrder.getMaterialCost(), "Checking material costs.");
    assertEquals(testOrder.getLabourCost(), retrievedOrder.getLabourCost(), "Checking labour costs.");
    assertEquals(testOrder.getTaxCost(), retrievedOrder.getTaxCost(), "Checking tax costs.");
    assertEquals(testOrder.getTotalCost(), retrievedOrder.getTotalCost(), "Checking total costs.");
    assertEquals(testOrder, retrievedOrder, "Checking order objects.");
    
}
    
}
