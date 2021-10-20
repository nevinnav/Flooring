/**
 *
 * @author nev
 * email: nevin.natividad@gmail.com
 * date: October 8, 2021
 * purpose: Final Assessment - Flooring Mastery
 */
package com.nev.flooringmastery.ui;

import com.nev.flooringmastery.dto.Order;
import com.nev.flooringmastery.dto.Product;
import com.nev.flooringmastery.dto.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Scanner;

public class FlooringMasteryView {

    private UserIO io;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Quit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public void displayAllOrders(Collection<Order> orders) {
        io.print("");
        io.print("Order   Customer   State    Product    Area    Total      Order");
        io.print("Number  Name       Abbrev.                     Cost       Date");
        io.print("===============================================================");
        orders.stream().forEach((p) -> io.print(p.getOrderNumber() + " "
                + p.getCustomerName() + " "
                + p.getStateAbbreviation() + " "
                + p.getProductType() + " "
                + p.getArea() + " sq. ft. " + " $"
                + p.getTotalCost() + " "
                + p.getOrderDate()));
        io.print("");
    }

    public void displayAllProducts(Collection<Product> products) {
        io.print("");
        io.print("Product    Material Cost    Labour Cost");
        io.print("Type       Per Sq. Ft.      Per Sq. Ft");
        io.print("=========================================");
        for (Product showProduct : products) {
            io.print(showProduct.getProductType() + " "
                    + " $" + showProduct.getCostPerSqFt()
                    + " $" + showProduct.getLabourPerSqFt());
        }
    }

    public void displayAllStates(Collection<StateTax> stateTax) {
        io.print("");
        io.print("State      State");
        io.print("Abbrev.    Name ");
        io.print("================");
        for (StateTax showState : stateTax) {
            io.print(showState.getStateAbbreviation() + "     "
                    + showState.getStateName());
        }

    }
    
    public void displaySuccessOrderProcess(Order order, String message) {
        io.print("");
        io.print("***Order Number " + order.getOrderNumber() + message + "***");
        io.readString("Press ENTER to return to Main Menu.");
        io.print("");
    }
    
    public void displayOrderNotProcessed(String message) {
        io.print("");
        io.print(message);
        io.readString("Press ENTER to return to Main Menu.");
        io.print("");
    }
    
    public void displayOrderToProcess(Order order, String message) {
        io.print("");
        io.print(message);
        io.print("==============");
        io.print("Order date: " + order.getOrderDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        io.print("Customer name: " + order.getCustomerName());
        io.print("State: " + order.getStateAbbreviation());
        io.print("Product: " + order.getProductType());
        io.print("Product Cost: $" + order.getCostPerSqFt() + " per sq. ft.");
        io.print("Labour Cost: $" + order.getLabourPerSqFt() + " per sq. ft.");
        io.print("Area: " + order.getArea() + " sq. ft.");
        io.print("Product Cost: $" + order.getMaterialCost());
        io.print("Labour Cost: " + order.getLabourCost());
        io.print("Tax Cost: " + order.getTaxCost());
        io.print("Total Cost: " + order.getTotalCost());
        io.print("");
    }

    public StateTax checkState(Collection<StateTax> stateTaxes, String stateString) {
        StateTax checkState = stateTaxes.stream().filter((p) -> p.getStateAbbreviation().equals(stateString))
                .findAny().orElse(null);
        return checkState;
    }

    public Product checkProduct(Collection<Product> productTypes, String productString) {
        Product checkProduct = productTypes.stream().filter((p) -> p.getProductType().equals(productString))
                .findAny().orElse(null);
        return checkProduct;
    }

    public LocalDate getOrderDate() {
        io.print("");
        LocalDate orderDate = io.readDate("Enter order date in format of yyyy-MM-dd.");
        return orderDate;
    }

    public LocalDate getFutureOrderDate() {
        io.print("");
        LocalDate orderDate = io.readFutureDate("Enter order date in the format of yyyy-dd-MM. Must be in the future.");
        return orderDate;
    }

    public int getOrderNumber() {
        io.print("");
        int orderNumber = io.readInt("Enter order number");
        return orderNumber;
    }

    public Order getNewOrderInfo(Collection<Product> productTypes, Collection<StateTax> stateTaxes) {
        //Initialize pending order
        Order pendingOrder = new Order();

        //Get order date
        io.print("");
        LocalDate orderDate = this.getFutureOrderDate();

        //Get customerName
        io.print("");
        String customerName = io.readCustomerName("Enter Customer Name. May contain the following characters:[A-Z][a-z][0-9][.][,]");

        //Display available states to user
        io.print("");
        this.displayAllStates(stateTaxes);

        //Get state and validate
        io.print("");
        String stateString = io.readString("Enter state of residence. Ex: CA for California, HI for Hawaii.");
        StateTax state = this.checkState(stateTaxes, stateString);
        while (state == null) {
            stateString = io.readString("\nEnter a valid state of residence.");
            state = this.checkState(stateTaxes, stateString);
        }

        //Display products to user
        io.print("");
        this.displayAllProducts(productTypes);

        //Get product and validate
        io.print("");
        String stringProduct = io.readString("Enter product type:");
        Product product = this.checkProduct(productTypes, stringProduct);
        while (product == null) {
            stringProduct = io.readString("\nEnter a valid product");
            product = this.checkProduct(productTypes, stringProduct);
        }

        //Get area
        io.print("");
        BigDecimal area = io.readBigDecimal("Enter area. Must be positive decimal and minimum order size is 100 sq. ft.", new BigDecimal(100));

        //Create pending order
        pendingOrder.setOrderDate(orderDate);
        pendingOrder.setCustomerName(customerName);
        pendingOrder.setStateAbbreviation(stateString);
        pendingOrder.setProductType(stringProduct);
        pendingOrder.setArea(area);
        pendingOrder.setTaxRate(state.getTaxRate());
        pendingOrder.setCostPerSqFt(product.getCostPerSqFt());
        pendingOrder.setLabourPerSqFt(product.getLabourPerSqFt());
        pendingOrder.setMaterialCost();
        pendingOrder.setLabourCost();
        pendingOrder.setTaxCost();
        pendingOrder.setTotalCost();

        return pendingOrder;
    }


    public Order getEditInfoOrder(Order editedOrder, Collection<Product> productTypes, Collection<StateTax> stateTaxes) {
        // Edit Customer Name
        io.print("");
        String newCustomerName = io.readString("Edit customer name or press enter to skip. May contain the following characters:[A-Z][a-z][0-9][.][,]");

        if (newCustomerName.isBlank()) {
            newCustomerName = editedOrder.getCustomerName();
        }
        while (!(newCustomerName.matches("^[A-Za-z0-9., ]+$"))) {
            newCustomerName = io.readString("\nEnter valid customer name or press enter to skip. May contain the following characters:[A-Z][a-z][0-9][.][,]");
            if (newCustomerName.isBlank()) {
                newCustomerName = editedOrder.getCustomerName();
            }
        }

        // Display all states
        io.print("");
        this.displayAllStates(stateTaxes);

        // Edit state and validate
        io.print("");
        String newState = io.readString("Edit state of residence or press enter to skip. Ex: CA for California, HI for Hawaii.");

        if (newState.isBlank()) {
            newState = editedOrder.getStateAbbreviation();
        }

        StateTax state = this.checkState(stateTaxes, newState);

        while (state == null) {
            newState = io.readString("\nEnter a valid state Ex: CA for California, HI for Hawaii.");

            if (newState.isBlank()) {
                newState = editedOrder.getStateAbbreviation();
            }
            state = this.checkState(stateTaxes, newState);
        }

        // Edit product and validate
        io.print("");
        this.displayAllProducts(productTypes);

        //Edit product and validate
        io.print("");
        String newProduct = io.readString("Edit product type or press enter to skip.");

        if (newProduct.isBlank()) {
            newProduct = editedOrder.getProductType();
        }

        Product product = this.checkProduct(productTypes, newProduct);

        while (product == null) {
            newProduct = io.readString("\nEnter valid product type or press enter to skip.");

            if (newProduct.isBlank()) {
                newProduct = editedOrder.getProductType();
            }
            product = this.checkProduct(productTypes, newProduct);
        }

        // Edit area and validate
        io.print("");

        String newAreaString = io.readString("Edit area or press enter to skip. Must be positive decimal and minimum order size is 100 sq. ft.");
        BigDecimal newArea = editedOrder.getArea();

        boolean isInvalidArea = true;

        do {
            try {
                
            if (newAreaString.isBlank()) {
                newArea = editedOrder.getArea();
                isInvalidArea = false;
            } else if (Integer.parseInt(newAreaString) >= 100) {
                newArea = new BigDecimal(newAreaString);
                isInvalidArea = false;
            } else if (Integer.parseInt(newAreaString) <100) {
                newAreaString = io.readString("\nEnter a valid area! Must be positive decimal and minimum order size is 100 sq. ft.");
            }
            } catch (NumberFormatException e) {
                newAreaString = io.readString("\nEnter a valid area! Must be positive decimal and minimum order size is 100 sq. ft.");
            }
        } while (isInvalidArea);

        // Set new edited order
        editedOrder.setOrderDate(editedOrder.getOrderDate());
        editedOrder.setOrderNumber(editedOrder.getOrderNumber());
        editedOrder.setCustomerName(newCustomerName);
        editedOrder.setStateAbbreviation(state.getStateAbbreviation());
        editedOrder.setProductType(product.getProductType());
        editedOrder.setArea(newArea);
        editedOrder.setTaxRate(state.getTaxRate());
        editedOrder.setCostPerSqFt(product.getCostPerSqFt());
        editedOrder.setLabourPerSqFt(product.getLabourPerSqFt());
        editedOrder.setMaterialCost();
        editedOrder.setLabourCost();
        editedOrder.setTaxCost();
        editedOrder.setTotalCost();
        return editedOrder;
    }

    public boolean displayPendingOrderAndConfirmOrder(Order pendingOrder) {
        //Display pending order information
        this.displayOrderToProcess(pendingOrder, "PENDING ORDER");

        //Confirm with user to process order
        String userConfirms = io.readString("Are you sure you want to add this order? (Y/N)");
        if (!userConfirms.equalsIgnoreCase("Y") && !userConfirms.equalsIgnoreCase("N")) {
            userConfirms = io.readString("Are you sure you want to add this order? (Y/N)");
        }

        if (userConfirms.equalsIgnoreCase("Y")) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean displayEditOrderAndConfirmOrder(Order orderToEdit) {
        // Display orderToEdit
        this.displayOrderToProcess(orderToEdit, "Order To Edit");
        
        //Confirm with user to edit order
        String userConfirms = io.readString("Are you sure you want to edit this order? (Y/N)");
        if (!userConfirms.equalsIgnoreCase("Y") && !userConfirms.equalsIgnoreCase("N")) {
            userConfirms = io.readString("Are you sure you want to add this order? (Y/N)");
        }

        if (userConfirms.equalsIgnoreCase("Y")) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean displayOrderToRemoveAndConfirm(Order orderToRemove) {
        // Display orderToRemove
        this.displayOrderToProcess(orderToRemove, "Order To Remove");
    
        //Confirm with user to process order
        String userConfirms = io.readString("Are you sure you want to remove this order? (Y/N)");
        if (!userConfirms.equalsIgnoreCase("Y") && !userConfirms.equalsIgnoreCase("N")) {
            userConfirms = io.readString("Are you sure you want to remove this order? (Y/N)");
        }

        if (userConfirms.equalsIgnoreCase("Y")) {
            return true;
        } else {
            return false;
        }
    }
    
    public void displayErrorMessage(String message) {
        io.print(message);
    }
}
