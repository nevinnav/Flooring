/**
 *
 * @author nev
 * email: nevin.natividad@gmail.com
 * date: October 8, 2021
 * purpose: Final Assessment - Flooring Mastery
 */
package com.nev.flooringmastery.service;

import com.nev.flooringmastery.dao.FlooringMasteryOrderDao;
import com.nev.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.nev.flooringmastery.dao.FlooringMasteryProductDao;
import com.nev.flooringmastery.dao.FlooringMasteryStateTaxDao;
import com.nev.flooringmastery.dto.Order;
import com.nev.flooringmastery.dto.Product;
import com.nev.flooringmastery.dto.StateTax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collection;

public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    FlooringMasteryOrderDao orderDao;
    FlooringMasteryProductDao productDao;
    FlooringMasteryStateTaxDao stateTaxDao;

    public FlooringMasteryServiceLayerImpl() {

    }

    public FlooringMasteryServiceLayerImpl(FlooringMasteryOrderDao orderDao,
            FlooringMasteryProductDao productDao, FlooringMasteryStateTaxDao stateTaxDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.stateTaxDao = stateTaxDao;
    }

    private void validateOrderDate(LocalDate orderDate) throws FlooringMasteryDataValidationException {
        if (orderDate.isBefore(LocalDate.now())) {
            throw new FlooringMasteryDataValidationException("Order date must be in future.");
        }
    }

    private void validateCustomerName(Order order) throws FlooringMasteryDataValidationException {
        if (order.getCustomerName() == null
                || order.getCustomerName().trim().length() == 0
                || !order.getCustomerName().matches("^[A-Za-z0-9., ]+$")) {
            throw new FlooringMasteryDataValidationException("Customer Name cannot be left blank or have invalid characters.");
        }
    }

    private void validateArea(Order order) throws FlooringMasteryDataValidationException {
        if (order.getArea().compareTo(new BigDecimal("100")) < 0) {
            throw new FlooringMasteryDataValidationException("Area must be a minimum of 100 square feet");
        }
    }

    private void validateState(Order order) throws FlooringMasteryDataValidationException, FlooringMasteryPersistenceException {
        if (this.getStateTax(order.getStateAbbreviation()) == null) {
            throw new FlooringMasteryDataValidationException("Products cannot be sold in " + order.getStateAbbreviation());
        }
    }

    private void validateProduct(Order order) throws FlooringMasteryDataValidationException {
        if (order.getProductType() == null) {
            throw new FlooringMasteryDataValidationException(order.getProductType() + " not part of item inventory.");
        }
    }

    @Override
    public Order processNewOrder(Order order)
            throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException {
        
        //Validate order properties
        this.validateOrderDate(order.getOrderDate());
        this.validateCustomerName(order);
        this.validateState(order);
        this.validateProduct(order);
        this.validateArea(order);

        //Set order number 
        int orderNumber = 1;
        try {
            Collection<Order> ordersByDate = this.getAllOrders(order.getOrderDate());
            orderNumber = ordersByDate.stream().mapToInt((p) -> p.getOrderNumber()).max().getAsInt();
            orderNumber++;
        } catch (FlooringMasteryNoOrderException e) {
            //Order Number will be 1.
        }
            
        order.setOrderNumber(orderNumber);
        return orderDao.addOrder(order);
    }

    @Override
    public Order processEditOrder(Order order)
            throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException {

        this.validateCustomerName(order);
        this.validateState(order);
        this.validateProduct(order);
        this.validateArea(order);
        return orderDao.editOrder(order);
    }

    @Override
    public Order getOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderException {
        Order viewOrder = orderDao.getOrder(orderDate, orderNumber);
        if (viewOrder == null) {
            throw new FlooringMasteryNoOrderException("No order found for order number " + orderNumber + " on order date " + orderDate + ".");
        }

        return viewOrder;
    }

    @Override
    public Collection<Order> getAllOrders(LocalDate orderDate) throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderException {
        Collection<Order> allOrders;
        try {
            allOrders = orderDao.getAllOrders(orderDate);
            if (allOrders.isEmpty()) {
                throw new FlooringMasteryNoOrderException("No orders found for order date " + orderDate);
            }
        } catch (FlooringMasteryPersistenceException e) {
            throw new FlooringMasteryNoOrderException("No orders found for order date " + orderDate);
        }

        return allOrders;
    }

    @Override
    public Order removeOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderException {
        Order deleteOrder = orderDao.removeOrder(orderDate, orderNumber);
        if (deleteOrder == null) {
            throw new FlooringMasteryNoOrderException("No order found for order number " + orderNumber + " on order date " + orderDate + ".");
        }
        return deleteOrder;
    }

    @Override
    public String exportAllOrders() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getProduct(String productType) throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException {
        Product chosenProduct = productDao.getProduct(productType);
        if (chosenProduct == null) {
            throw new FlooringMasteryDataValidationException(productType + " is not an option from inventory.");
        }
        return chosenProduct;
    }

    @Override
    public Collection<Product> getAllProducts() throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException {
        return productDao.getAllProducts();
    }

    @Override
    public StateTax getStateTax(String stateAbbreviation) throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException {
        StateTax enteredState = stateTaxDao.getStateTax(stateAbbreviation);
        if (enteredState == null) {
            throw new FlooringMasteryDataValidationException("Products cannot be sold to in " + enteredState + ".");
        }
        return enteredState;
    }

    @Override
    public Collection<StateTax> getAllStateTax() throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException {
        return stateTaxDao.getAllStateTax();
    }

}
