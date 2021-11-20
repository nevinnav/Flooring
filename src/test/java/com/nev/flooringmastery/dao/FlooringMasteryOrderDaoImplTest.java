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
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class FlooringMasteryOrderDaoImplTest {
    
    FlooringMasteryOrderDao testOrderDao;
    
    public FlooringMasteryOrderDaoImplTest() {
        
    }
    
    @BeforeEach
    public void setUp() throws IOException {
        //testOrderDao = new FlooringMasteryOrderDaoImpl("Test/Orders_");
        String testFile = "Test/testOrder_";
        new FileWriter(testFile);
        testOrderDao = new FlooringMasteryOrderDaoImpl(testFile);
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
    testOrder.setOrderDate(LocalDate.now().plusDays(1));
    
    //Add testOrder to the testDao
    testOrderDao.addOrder(testOrder);
    //Get order from the DAO
    Order retrievedOrder = testOrderDao.getOrder(testOrder.getOrderDate(), testOrder.getOrderNumber());
    
    //Check the data is equal
    assertEquals(testOrder, retrievedOrder, "Checking order objects.");
}
    
    @Test
    public void testAddAndGetAllOrders() throws Exception {
    //Set up test order 1
    Order testOrder1 = new Order();
    testOrder1.setOrderNumber(1);
    testOrder1.setCustomerName("Stephen Curry");
    testOrder1.setStateAbbreviation("CA");
    testOrder1.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
    testOrder1.setProductType("Hardwood");
    testOrder1.setArea(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
    testOrder1.setCostPerSqFt(new BigDecimal("1.00").setScale(2, RoundingMode.HALF_UP));
    testOrder1.setLabourPerSqFt(new BigDecimal("1.00").setScale(2, RoundingMode.HALF_UP));
    testOrder1.setMaterialCost();
    testOrder1.setLabourCost();
    testOrder1.setTaxCost();
    testOrder1.setTotalCost();
    testOrder1.setOrderDate(LocalDate.parse("2021-10-31"));
    
    //Set up test order 2
    Order testOrder2 = new Order();
    testOrder2.setOrderNumber(2);
    testOrder2.setCustomerName("Klay Thompson");
    testOrder2.setStateAbbreviation("CA");
    testOrder2.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
    testOrder2.setProductType("Class Hardwood");
    testOrder2.setArea(new BigDecimal("1000").setScale(2, RoundingMode.HALF_UP));
    testOrder2.setCostPerSqFt(new BigDecimal("2.00").setScale(2, RoundingMode.HALF_UP));
    testOrder2.setLabourPerSqFt(new BigDecimal("2.00").setScale(2, RoundingMode.HALF_UP));
    testOrder2.setMaterialCost();
    testOrder2.setLabourCost();
    testOrder2.setTaxCost();
    testOrder2.setTotalCost();
    testOrder2.setOrderDate(LocalDate.parse("2021-10-31"));
    
    //Add orders to DAO
    testOrderDao.addOrder(testOrder1);
    testOrderDao.addOrder(testOrder2);
    
    Collection<Order> testOrders = testOrderDao.getAllOrders(LocalDate.parse("2021-10-31"));
    
    // Check general contents of the list
    assertNotNull(testOrders, "The list of orders must NOT be null");
    assertEquals(2, testOrders.size(), "List of orders should have 2 orders.");
    
    // Check the specifics
    assertTrue(testOrderDao.getAllOrders(LocalDate.parse("2021-10-31")).contains(testOrder1),
            "The list of orders should include Stephen.");
    assertTrue(testOrderDao.getAllOrders(LocalDate.parse("2021-10-31")).contains(testOrder2),
            "The list of orders should include Klay.");
    }
    
    @Test
    public void testRemoveOrder() throws Exception {
    //Set up test order 1
    Order testOrder1 = new Order();
    testOrder1.setOrderNumber(1);
    testOrder1.setCustomerName("Stephen Curry");
    testOrder1.setStateAbbreviation("CA");
    testOrder1.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
    testOrder1.setProductType("Hardwood");
    testOrder1.setArea(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
    testOrder1.setCostPerSqFt(new BigDecimal("1.00").setScale(2, RoundingMode.HALF_UP));
    testOrder1.setLabourPerSqFt(new BigDecimal("1.00").setScale(2, RoundingMode.HALF_UP));
    testOrder1.setMaterialCost();
    testOrder1.setLabourCost();
    testOrder1.setTaxCost();
    testOrder1.setTotalCost();
    testOrder1.setOrderDate(LocalDate.parse("2021-10-31"));
    
    //Set up test order 2
    Order testOrder2 = new Order();
    testOrder2.setOrderNumber(2);
    testOrder2.setCustomerName("Klay Thompson");
    testOrder2.setStateAbbreviation("CA");
    testOrder2.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
    testOrder2.setProductType("Class Hardwood");
    testOrder2.setArea(new BigDecimal("1000").setScale(2, RoundingMode.HALF_UP));
    testOrder2.setCostPerSqFt(new BigDecimal("2.00").setScale(2, RoundingMode.HALF_UP));
    testOrder2.setLabourPerSqFt(new BigDecimal("2.00").setScale(2, RoundingMode.HALF_UP));
    testOrder2.setMaterialCost();
    testOrder2.setLabourCost();
    testOrder2.setTaxCost();
    testOrder2.setTotalCost();
    testOrder2.setOrderDate(LocalDate.parse("2021-10-31"));
    
    //Add orders to DAO
    testOrderDao.addOrder(testOrder1);
    testOrderDao.addOrder(testOrder2);
    
    //Remove the first order - testOrder1 (Steph)
    Order removedOrder = testOrderDao.removeOrder(LocalDate.parse("2021-10-31"), 1);
    
    //Check the correct order was removed
    assertEquals(removedOrder, testOrder1, "The order removed was order number 1 for Stephen.");
    
    //Get all orders
    Collection<Order> allOrders = testOrderDao.getAllOrders(LocalDate.parse("2021-10-31"));
    
    //Check general contents
    assertNotNull(allOrders, "All orders should NOT be null.");
    assertEquals(1, allOrders.size(), "All orders should have 1 order.");
    
    //Check the specifics
    assertFalse(allOrders.contains(testOrder1), "All orders should not include order number 1 for Stephen.");
    assertTrue(allOrders.contains(testOrder2), "All orders should include order number 2 for Klay.");
    
    //Remove testOrder2
    removedOrder = testOrderDao.removeOrder(LocalDate.parse("2021-10-31"), 2);
    assertEquals(removedOrder, testOrder2, "The removed order should be number 2 for Klay.");
    
    //Get all orders
    allOrders = testOrderDao.getAllOrders(LocalDate.parse("2021-10-31"));
    
    //Check contents
    assertTrue(allOrders.isEmpty(), "The retrieved list of orders should be empty.");
    
    //Try to get both orders by their order number
    Order retrievedOrder = testOrderDao.getOrder(LocalDate.parse("2021-10-31"), 1);
    assertNull(retrievedOrder, "Order 1 was removed, should be null");
    
    retrievedOrder = testOrderDao.getOrder(LocalDate.parse("2021-10-31"), 2);
    assertNull(retrievedOrder, "Charles was removed, should be null");
    
    }

    
    @Test
    public void testAddAndEditOrder() throws Exception {
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
    testOrder.setOrderDate(LocalDate.parse("2021-10-31"));
    
    //Add testOrder to the testDao
    testOrderDao.addOrder(testOrder);
    //Get order from the DAO
    Order retrievedOrder = testOrderDao.getOrder(testOrder.getOrderDate(), testOrder.getOrderNumber());
    
    //Check the data is equal
    assertEquals(testOrder, retrievedOrder, "Checking order objects.");
    
    //Edit testOrder (Name, State, Product, and Area) and set properties
    Order editedOrder = testOrderDao.getOrder(LocalDate.parse("2021-10-31"), 1);
    editedOrder.setOrderNumber(testOrder.getOrderNumber());
    editedOrder.setCustomerName("Klay Thompson");
    editedOrder.setStateAbbreviation(testOrder.getStateAbbreviation());         //Stays the same
    editedOrder.setTaxRate(testOrder.getTaxRate());                             //Stays the same
    editedOrder.setProductType("Classic Hardwood");
    editedOrder.setArea(new BigDecimal("1000").setScale(2, RoundingMode.HALF_UP));
    editedOrder.setCostPerSqFt(testOrder.getCostPerSqFt());                     //Stays the same
    editedOrder.setLabourPerSqFt(testOrder.getLabourPerSqFt());                 //Stays the same
    editedOrder.setMaterialCost();
    editedOrder.setLabourCost();
    editedOrder.setTaxCost();
    editedOrder.setTotalCost();
    editedOrder.setOrderDate(LocalDate.parse("2021-10-31"));
    
    //Add editedOrder to the testDao
    testOrderDao.editOrder(editedOrder);
    //
    Order retrievedEditedOrder = testOrderDao.getOrder(LocalDate.parse("2021-10-31"), 1);
    
    //Confirm original and edited which stayed the same and are changed
    assertEquals(testOrder.getOrderNumber(), retrievedOrder.getOrderNumber(),
            "Checking order stays the same.");
    assertEquals(testOrder.getOrderDate(), retrievedOrder.getOrderDate(),
            "Checking order date stays the same.");
    assertNotEquals(testOrder.getCustomerName(), retrievedEditedOrder.getCustomerName(),
            "Checking customer names are different.");
    assertEquals(testOrder.getStateAbbreviation(), retrievedEditedOrder.getStateAbbreviation(),
            "Checking customer state stays the same.");
    assertEquals(testOrder.getTaxRate(), retrievedEditedOrder.getTaxRate(), 
            "Checking tax rates stays the same.");
    assertNotEquals(testOrder.getProductType(), retrievedEditedOrder.getProductType(),
            "Checking products are different.");
    assertNotEquals(testOrder.getArea(), retrievedEditedOrder.getArea(),
            "Checking areas are different.");
    assertEquals(testOrder.getCostPerSqFt(), retrievedEditedOrder.getCostPerSqFt(),
            "Checking product cost per sq ft stays the same.");
    assertEquals(testOrder.getLabourPerSqFt(), retrievedEditedOrder.getLabourPerSqFt(),
            "Checking labour cost per sq ft stays the same.");
    assertNotEquals(testOrder.getMaterialCost(), retrievedEditedOrder.getMaterialCost(),
            "Checking material costs are different.");
    assertNotEquals(testOrder.getTaxCost(), retrievedEditedOrder.getTaxCost(),
            "Checking tax costs are different.");
    assertNotEquals(testOrder.getLabourCost(), retrievedEditedOrder.getLabourCost(),
            "Checking labour costs are different.");
    assertNotEquals(testOrder.getTotalCost(), retrievedEditedOrder.getTotalCost(),
            "Checking total costs are different.");
    }

    @Test
    public void testExportAllData() throws Exception {
    //Set up test order 1
    Order testOrder1 = new Order();
    testOrder1.setOrderNumber(1);
    testOrder1.setCustomerName("Stephen Curry");
    testOrder1.setStateAbbreviation("CA");
    testOrder1.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
    testOrder1.setProductType("Hardwood");
    testOrder1.setArea(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
    testOrder1.setCostPerSqFt(new BigDecimal("1.00").setScale(2, RoundingMode.HALF_UP));
    testOrder1.setLabourPerSqFt(new BigDecimal("1.00").setScale(2, RoundingMode.HALF_UP));
    testOrder1.setMaterialCost();
    testOrder1.setLabourCost();
    testOrder1.setTaxCost();
    testOrder1.setTotalCost();
    testOrder1.setOrderDate(LocalDate.parse("2021-10-31"));
    
    //Set up test order 2
    Order testOrder2 = new Order();
    testOrder2.setOrderNumber(2);
    testOrder2.setCustomerName("Klay Thompson");
    testOrder2.setStateAbbreviation("CA");
    testOrder2.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
    testOrder2.setProductType("Class Hardwood");
    testOrder2.setArea(new BigDecimal("1000").setScale(2, RoundingMode.HALF_UP));
    testOrder2.setCostPerSqFt(new BigDecimal("2.00").setScale(2, RoundingMode.HALF_UP));
    testOrder2.setLabourPerSqFt(new BigDecimal("2.00").setScale(2, RoundingMode.HALF_UP));
    testOrder2.setMaterialCost();
    testOrder2.setLabourCost();
    testOrder2.setTaxCost();
    testOrder2.setTotalCost();
    testOrder2.setOrderDate(LocalDate.parse("2021-10-31"));
    
    //Add orders to DAO
    testOrderDao.addOrder(testOrder1);
    testOrderDao.addOrder(testOrder2);
    
    //Export both order to export data file
    testOrderDao.exportAllOrders();
    
    
    }
    
}
