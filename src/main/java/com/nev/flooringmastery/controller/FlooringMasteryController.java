/**
 *
 * @author nev
 * email: nevin.natividad@gmail.com
 * date: October 8, 2021
 * purpose: Final Assessment - Flooring Mastery
 */
package com.nev.flooringmastery.controller;

import com.nev.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.nev.flooringmastery.dto.Order;
import com.nev.flooringmastery.dto.Product;
import com.nev.flooringmastery.dto.StateTax;
import com.nev.flooringmastery.service.FlooringMasteryDataValidationException;
import com.nev.flooringmastery.service.FlooringMasteryNoOrderException;
import com.nev.flooringmastery.service.FlooringMasteryServiceLayer;
import com.nev.flooringmastery.ui.FlooringMasteryView;
import com.nev.flooringmastery.ui.UserIO;
import com.nev.flooringmastery.ui.UserIOConsoleImpl;
import java.time.LocalDate;
import java.util.Collection;

public class FlooringMasteryController {

    private FlooringMasteryServiceLayer service;
    private FlooringMasteryView view;
    private UserIO io = new UserIOConsoleImpl();

    public FlooringMasteryController(FlooringMasteryServiceLayer service, FlooringMasteryView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        while (keepGoing) {
            try {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        displayAllOrders();
                        break;
                    case 2:
                        addOrderProcess();
                        break;
                    case 3:
                        editOrderProcess();
                        break;
                    case 4:
                        removeOrderProcess();
                        break;
                    case 5:
                        io.print("Export All Data");
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        io.print("UNKNOWN COMMAND");

                }
            } catch (FlooringMasteryPersistenceException | FlooringMasteryNoOrderException
                    | FlooringMasteryDataValidationException e) {
                displayErrorMessage(e.getMessage()); 
            } 
        }
        io.print("GOOD BYE");
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayAllOrders() throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderException {
        // Get order date from user
        LocalDate selectedOrderDate = view.getOrderDate();
        // Get all orders using order date from user
        Collection<Order> ordersForOrderDate = service.getAllOrders(selectedOrderDate);
        // Display all orders
        view.displayAllOrders(ordersForOrderDate);
    }

    private void addOrderProcess() throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException {
        // Get order info from user
        Order pendingOrder = view.getNewOrderInfo(service.getAllProducts(), service.getAllStateTax());
        // Display pending order and confirm Y or N
        boolean confirmation = view.displayPendingOrderAndConfirmOrder(pendingOrder);
        // If yes, process pendingOrder
        if (confirmation) {
            Order addedOrder = service.processNewOrder(pendingOrder);
            //Display Confirmation
            view.displaySuccessOrderProcess(addedOrder, " was added.");
        } else {
            //Display No Order Processed
            view.displayOrderNotProcessed("Order NOT added.");
        }
    }

    private void editOrderProcess() throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderException, FlooringMasteryDataValidationException {
        // Get order date from user
        LocalDate selectedOrderDate = view.getOrderDate();
        // Display all orders using order date from user
        Collection<Order> ordersForOrderDate = service.getAllOrders(selectedOrderDate);
        view.displayAllOrders(ordersForOrderDate);
        // Get order number to edit
        int selectedOrderNumber = view.getOrderNumber();
        // Get order to edit and get new info to edit
        Order pendingEditOrder = service.getOrder(selectedOrderDate, selectedOrderNumber);
        // Display order to edit
        view.displayOrderToProcess(pendingEditOrder, "ORDER TO EDIT");
        // Get edited order information
        Order editedOrder = view.getEditInfoOrder(pendingEditOrder, service.getAllProducts(), service.getAllStateTax());
        // Display editedOrder and confirm to process Y/N
        boolean confirmation = view.displayEditOrderAndConfirmOrder(editedOrder);
        // If yes, process editOrder
        if (confirmation) {
            Order newEditedOrder = service.processEditOrder(editedOrder);
            // Display confirmation
            view.displaySuccessOrderProcess(newEditedOrder, " was edited.");
        } else {
            // Display no order edited
        }   view.displayOrderNotProcessed("Order NOT edited.");
    }

    private void removeOrderProcess() throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderException {
        // Get order date from user
        LocalDate selectedOrderDate = view.getOrderDate();
        // Display all orders using order date from user
        Collection<Order> ordersForOrderDate = service.getAllOrders(selectedOrderDate);
        view.displayAllOrders(ordersForOrderDate);
        // Get order number to remove
        int selectedOrderNumber = view.getOrderNumber();
        // Get order to remove
        Order orderToRemove = service.getOrder(selectedOrderDate, selectedOrderNumber);
        // Display order to remove and confirm to process Y/N
        boolean confirmation = view.displayOrderToRemoveAndConfirm(orderToRemove);
        // If user confirms, remove order
        if (confirmation) {
            Order deletedOrder = service.removeOrder(selectedOrderDate, selectedOrderNumber);
            //Display confirmation
            view.displaySuccessOrderProcess(deletedOrder, " was removed.");
        } else {
            //Display not processed
            view.displayOrderNotProcessed("Order NOT removed.");
        }
        
    }
    
    private void displayErrorMessage(String message) {
        view.displayErrorMessage(message);
    }
}
