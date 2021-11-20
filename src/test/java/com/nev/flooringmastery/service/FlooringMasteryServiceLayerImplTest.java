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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class FlooringMasteryServiceLayerImplTest {
    
    FlooringMasteryServiceLayer service;
    
    public FlooringMasteryServiceLayerImplTest() {
    AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
    appContext.scan("com.nev.flooringmastery.service");
    appContext.refresh();
    
    service = appContext.getBean("flooringMasteryServiceLayerImpl", FlooringMasteryServiceLayerImpl.class);
    
    /*
        FlooringMasteryOrderDao orderDao = new FlooringMasteryOrderDaoStubImpl();
        FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoStubImpl();
        FlooringMasteryStateTaxDao stateTaxDao = new FlooringMasteryStateTaxDaoStubImpl();
        FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoStubImpl();
        
        service = new FlooringMasteryServiceLayerImpl(orderDao, productDao, stateTaxDao, auditDao);
    **/
    }
    
    @Test
    public void testValidNewOrder() {
        Order newOrder = new Order();
        newOrder.setOrderNumber(1);
        newOrder.setCustomerName("Stephen Curry");
        newOrder.setStateAbbreviation("CA");
        newOrder.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
        newOrder.setProductType("Carpet");
        newOrder.setArea(new BigDecimal("200").setScale(2, RoundingMode.HALF_UP));
        newOrder.setCostPerSqFt(new BigDecimal("3.99").setScale(2, RoundingMode.HALF_UP));
        newOrder.setLabourPerSqFt(new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP));
        newOrder.setMaterialCost();
        newOrder.setLabourCost();
        newOrder.setTaxCost();
        newOrder.setTotalCost();
        newOrder.setOrderDate(LocalDate.now().plusDays(1));
    
        try {
            service.processNewOrder(newOrder);
        } catch (FlooringMasteryPersistenceException | FlooringMasteryDataValidationException e) {
            fail("Valid new order should not throw any exceptions");
        }
    }
    
    @Test
    public void testNewOrderInvalidCustomerName() throws Exception {
        Order newOrder = new Order();
        newOrder.setOrderNumber(2);
        newOrder.setCustomerName("");
        newOrder.setStateAbbreviation("CA");
        newOrder.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
        newOrder.setProductType("Carpet");
        newOrder.setArea(new BigDecimal("200").setScale(2, RoundingMode.HALF_UP));
        newOrder.setCostPerSqFt(new BigDecimal("3.99").setScale(2, RoundingMode.HALF_UP));
        newOrder.setLabourPerSqFt(new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP));
        newOrder.setMaterialCost();
        newOrder.setLabourCost();
        newOrder.setTaxCost();
        newOrder.setTotalCost();
        newOrder.setOrderDate(LocalDate.now().plusDays(1));
        
        try {
            service.processNewOrder(newOrder);
            fail("Expected validation exception was not thrown.");
        } catch (FlooringMasteryPersistenceException  e) {
            fail("Incorrect exception was thrown.");
        } catch (FlooringMasteryDataValidationException e) {
            return;
        }   
    }
            
    @Test
    public void testNewOrderInvalidOrderDate() throws Exception {
        Order newOrder = new Order();
        newOrder.setOrderNumber(1);
        newOrder.setCustomerName("Stephen Curry");
        newOrder.setStateAbbreviation("CA");
        newOrder.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
        newOrder.setProductType("Carpet");
        newOrder.setArea(new BigDecimal("200").setScale(2, RoundingMode.HALF_UP));
        newOrder.setCostPerSqFt(new BigDecimal("3.99").setScale(2, RoundingMode.HALF_UP));
        newOrder.setLabourPerSqFt(new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP));
        newOrder.setMaterialCost();
        newOrder.setLabourCost();
        newOrder.setTaxCost();
        newOrder.setTotalCost();
        newOrder.setOrderDate(LocalDate.parse("2020-10-22"));
    
        try {
            service.processNewOrder(newOrder);
            fail("Expected validation exception was not thrown.");
        } catch (FlooringMasteryPersistenceException e) {
            fail("Incorrect exception was thrown.");
        } catch (FlooringMasteryDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testNewOrderInvalidState() throws Exception {
        Order newOrder = new Order();
        newOrder.setOrderNumber(1);
        newOrder.setCustomerName("Stephen Curry");
        newOrder.setStateAbbreviation("NY");
        newOrder.setTaxRate(new BigDecimal("10.00").setScale(2, RoundingMode.HALF_UP));
        newOrder.setProductType("Carpet");
        newOrder.setArea(new BigDecimal("200").setScale(2, RoundingMode.HALF_UP));
        newOrder.setCostPerSqFt(new BigDecimal("3.99").setScale(2, RoundingMode.HALF_UP));
        newOrder.setLabourPerSqFt(new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP));
        newOrder.setMaterialCost();
        newOrder.setLabourCost();
        newOrder.setTaxCost();
        newOrder.setTotalCost();
        newOrder.setOrderDate(LocalDate.now().plusDays(1));
        
        try {
            service.processNewOrder(newOrder);
            fail("Expected validation exception was not thrown.");
        } catch (FlooringMasteryPersistenceException e) {
            fail("Incorrect exception was thrown");
        } catch (FlooringMasteryDataValidationException e) {
            return;
        }
    }

    @Test
    public void testNewOrderInvalidProduct() throws Exception {
        Order newOrder = new Order();
        newOrder.setOrderNumber(1);
        newOrder.setCustomerName("Stephen Curry");
        newOrder.setStateAbbreviation("CA");
        newOrder.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
        newOrder.setProductType("Brick");
        newOrder.setArea(new BigDecimal("200").setScale(2, RoundingMode.HALF_UP));
        newOrder.setCostPerSqFt(new BigDecimal("3.99").setScale(2, RoundingMode.HALF_UP));
        newOrder.setLabourPerSqFt(new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP));
        newOrder.setMaterialCost();
        newOrder.setLabourCost();
        newOrder.setTaxCost();
        newOrder.setTotalCost();
        newOrder.setOrderDate(LocalDate.now().plusDays(1));
        
        try {
            service.processNewOrder(newOrder);
            fail("Expected validation exception was not thrown.");
        } catch (FlooringMasteryPersistenceException e) {
            fail("Incorrect exception was thrown");
        } catch (FlooringMasteryDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testNewOrderInvalidArea() throws Exception {
        Order newOrder = new Order();
        newOrder.setOrderNumber(1);
        newOrder.setCustomerName("Stephen Curry");
        newOrder.setStateAbbreviation("CA");
        newOrder.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
        newOrder.setProductType("Carpet");
        newOrder.setArea(new BigDecimal("50").setScale(2, RoundingMode.HALF_UP));
        newOrder.setCostPerSqFt(new BigDecimal("3.99").setScale(2, RoundingMode.HALF_UP));
        newOrder.setLabourPerSqFt(new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP));
        newOrder.setMaterialCost();
        newOrder.setLabourCost();
        newOrder.setTaxCost();
        newOrder.setTotalCost();
        newOrder.setOrderDate(LocalDate.now().plusDays(1));
        
        try {
            service.processNewOrder(newOrder);
            fail("Expected validation exception was not thrown.");
        } catch (FlooringMasteryPersistenceException e) {
            fail("Incorrect exception was thrown");
        } catch (FlooringMasteryDataValidationException e) {
            return;
        }
    }    
   
    @Test
    public void testGetAllOrders() throws Exception {
        Order newOrder = new Order();
        newOrder.setOrderNumber(1);
        newOrder.setCustomerName("Stephen Curry");
        newOrder.setStateAbbreviation("CA");
        newOrder.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
        newOrder.setProductType("Carpet");
        newOrder.setArea(new BigDecimal("200").setScale(2, RoundingMode.HALF_UP));
        newOrder.setCostPerSqFt(new BigDecimal("3.99").setScale(2, RoundingMode.HALF_UP));
        newOrder.setLabourPerSqFt(new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP));
        newOrder.setMaterialCost();
        newOrder.setLabourCost();
        newOrder.setTaxCost();
        newOrder.setTotalCost();
        newOrder.setOrderDate(LocalDate.now().plusDays(1));
        
        assertEquals(1, service.getAllOrders(LocalDate.now().plusDays(1)).size(), "Should only have one order.");
        assertTrue(service.getAllOrders(LocalDate.now().plusDays(1)).contains(newOrder), "The one student should be for Stephen.");
    }
    
    @Test
    public void testGetOrder() throws Exception {
        Order newOrder = new Order();
        newOrder.setOrderNumber(1);
        newOrder.setCustomerName("Stephen Curry");
        newOrder.setStateAbbreviation("CA");
        newOrder.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
        newOrder.setProductType("Carpet");
        newOrder.setArea(new BigDecimal("200").setScale(2, RoundingMode.HALF_UP));
        newOrder.setCostPerSqFt(new BigDecimal("3.99").setScale(2, RoundingMode.HALF_UP));
        newOrder.setLabourPerSqFt(new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP));
        newOrder.setMaterialCost();
        newOrder.setLabourCost();
        newOrder.setTaxCost();
        newOrder.setTotalCost();
        newOrder.setOrderDate(LocalDate.now().plusDays(1));
        
        service.processNewOrder(newOrder);
        
        Order shouldBeStephen = service.getOrder(LocalDate.now().plusDays(1), 2);
        assertNotNull(shouldBeStephen, "Getting order 1 should NOT be null");
        assertEquals(shouldBeStephen, newOrder, "Order 1 should be for Stephen.");
        
        //Order shouldBeNull = service.getOrder(LocalDate.now().plusDays(1), 5);
        //assertNull(shouldBeNull, "Getting Order 5 on 10/01/2021 should be null.");
    }
    
    @Test
    public void testRemoveOrder() throws Exception {
        Order newOrder = new Order();
        newOrder.setOrderNumber(1);
        newOrder.setCustomerName("Stephen Curry");
        newOrder.setStateAbbreviation("CA");
        newOrder.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
        newOrder.setProductType("Carpet");
        newOrder.setArea(new BigDecimal("200").setScale(2, RoundingMode.HALF_UP));
        newOrder.setCostPerSqFt(new BigDecimal("3.99").setScale(2, RoundingMode.HALF_UP));
        newOrder.setLabourPerSqFt(new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP));
        newOrder.setMaterialCost();
        newOrder.setLabourCost();
        newOrder.setTaxCost();
        newOrder.setTotalCost();
        newOrder.setOrderDate(LocalDate.now().plusDays(1));
        
        service.processNewOrder(newOrder);
        
        //Order shouldBeStephen = service.removeOrder(LocalDate.now().plusDays(1), 1);
        //assertNotNull(shouldBeStephen, "Getting order 1 should NOT be null");
        //assertEquals(shouldBeStephen, newOrder, "Order 1 should be for Stephen.");
        
        //Order shouldBeNull = service.removeOrder(LocalDate.now().plusDays(1), 1);
        //assertNull(shouldBeNull, "Removing order 1 should make order null");
        
        //shouldBeNull = service.removeOrder(LocalDate.now().plusDays(1), 5);
        //assertNull(shouldBeNull, "Removing order 5 should be null");
    }
    
    //@Test
    //public void testEditOrder() throws Exception {
        
    //}
        
    
    
    
}
