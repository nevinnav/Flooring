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
import com.nev.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlooringMasteryOrderDaoStubImpl implements FlooringMasteryOrderDao {
   
    public Order onlyOrder;

    Map<Integer, Order> orderList = new HashMap<>();
    
    @Autowired
    public FlooringMasteryOrderDaoStubImpl() {
        onlyOrder = new Order();
        onlyOrder.setOrderNumber(1);
        onlyOrder.setCustomerName("Stephen Curry");
        onlyOrder.setStateAbbreviation("CA");
        onlyOrder.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setProductType("Carpet");
        onlyOrder.setArea(new BigDecimal("200").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setCostPerSqFt(new BigDecimal("3.99").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setLabourPerSqFt(new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setMaterialCost();
        onlyOrder.setLabourCost();
        onlyOrder.setTaxCost();
        onlyOrder.setTotalCost();
        onlyOrder.setOrderDate(LocalDate.now().plusDays(1));
    }
    
    public FlooringMasteryOrderDaoStubImpl(Order testOrder) {
        this.onlyOrder = testOrder;
    }

    @Override
    public Order getOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryPersistenceException {
        if (onlyOrder.getOrderNumber() == orderNumber && onlyOrder.getOrderDate().compareTo(orderDate) == 0) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Collection<Order> getAllOrders(LocalDate orderDate) throws FlooringMasteryPersistenceException {
            orderList.put(onlyOrder.getOrderNumber(), onlyOrder);
            return orderList.values();
    }

    @Override
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException {
        onlyOrder = order;
        return onlyOrder;
    }

    @Override
    public Order editOrder(Order order) throws FlooringMasteryPersistenceException {
        if (onlyOrder.getOrderDate() == order.getOrderDate() && 
                onlyOrder.getOrderNumber() == order.getOrderNumber()) {
            return order;
        } else {
            return null;
        }
    }

    @Override
    public Order removeOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryPersistenceException {
        if (onlyOrder.getOrderNumber() == orderNumber && onlyOrder.getOrderDate().compareTo(orderDate) ==0 ) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public String exportAllOrders() throws FlooringMasteryPersistenceException {
        return null;
    }
}
