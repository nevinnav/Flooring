/**
 *
 * @author nev
 * email: nevin.natividad@gmail.com
 * date: October 8, 2021
 * purpose: Final Assessment - Flooring Mastery
 */
package com.nev.flooringmastery.dao;

import com.nev.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class FlooringMasteryOrderDaoImpl implements FlooringMasteryOrderDao {

    public String fileFormat = "Orders/Orders_";

    public static final String DELIMITER = "__";

    public FlooringMasteryOrderDaoImpl() {

    }

    public FlooringMasteryOrderDaoImpl(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    private Map<Integer, Order> orders = new HashMap<>();

    @Override
    public Order getOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryPersistenceException {
        this.loadOrders(orderDate);
        return orders.get(orderNumber);
    }

    @Override
    public Collection<Order> getAllOrders(LocalDate orderDate) throws FlooringMasteryPersistenceException {
        this.loadOrders(orderDate);
        return orders.values();
    }

    @Override
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException {
        try {
            this.loadOrders(order.getOrderDate());
        } catch (FlooringMasteryPersistenceException e) {
            this.orders = new HashMap<>();
        }

        orders.put(order.getOrderNumber(), order);
        this.writeOrders(order.getOrderDate());
        return order;
    }

    @Override
    public Order editOrder(Order order) throws FlooringMasteryPersistenceException {
        this.loadOrders(order.getOrderDate());
        Order orderToEdit = getOrder(order.getOrderDate(), order.getOrderNumber());
        this.orders.put(orderToEdit.getOrderNumber(), order);
        this.writeOrders(order.getOrderDate());
        return order;
    }

    @Override
    public Order removeOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryPersistenceException {
        this.loadOrders(orderDate);
        Order orderToDelete = orders.remove(orderNumber);
        this.writeOrders(orderDate);
        return orderToDelete;
    }

    @Override
    public String exportAllOrders() throws FlooringMasteryPersistenceException {
        String exportFile = "DataExport.txt";
        String orderFileHeader = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot, "
                + "LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total,OrderDate";
        
        //File directory of orders
        String fileDirectory = "Orders/";
        File orderDirectory = new File(fileDirectory);
        //File list in array of orderFiles in directory
        File[] orderFiles = orderDirectory.listFiles();

        Scanner scanner;
        PrintWriter out;
        //Create PrintWriter to write out all orders to the file
        try{
            out = new PrintWriter(new FileWriter(exportFile));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("-_- Could not save data", e);
        }
        
        out.println(orderFileHeader);
        
        //Load order files
        for (File eachFile : orderFiles) {
            String fileName = eachFile.getName();
            //[Orders_MMddyyyy.txt]
            String stringDate = fileName.substring(7, 15);
            //Get orderdate from fileName
            LocalDate orderDate = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("MMddyyyy"));

            try {
                //Create Scanner for read all the order files from Orders Directory
                scanner = new Scanner(new BufferedReader(new FileReader("Orders/" + fileName)));
            } catch (FileNotFoundException e) {
                throw new FlooringMasteryPersistenceException("-_- Could not load order file with orderdate.", e);
            }
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                            
                while (scanner.hasNextLine()) {
                    String currentOrder = scanner.nextLine();
                    out.println(currentOrder + "__" + orderDate.format(DateTimeFormatter.ofPattern("MMddyyyy")));
                    out.flush();
                }
                
                
        } 
        out.close();
        return exportFile;
    }
    

    private Order unmarshallOrder(String orderAsText) {
        // orderAsTest is expecting a line read in from our file.
        // OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,
        // LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total
        // 2,Doctor Who,WA,9.25,Wood,243.00,5.15,4.75,1251.45,1154.25,216.51,2622.21

        String[] orderTokens = orderAsText.split(DELIMITER);

        // Create new Order object
        Order orderFromFile = new Order();
        orderFromFile.setOrderNumber(Integer.parseInt(orderTokens[0]));
        orderFromFile.setCustomerName(orderTokens[1]);
        orderFromFile.setStateAbbreviation(orderTokens[2]);
        orderFromFile.setTaxRate(new BigDecimal(orderTokens[3]).setScale(2, RoundingMode.HALF_UP));
        orderFromFile.setProductType(orderTokens[4]);
        orderFromFile.setArea(new BigDecimal(orderTokens[5]).setScale(2, RoundingMode.HALF_UP));
        orderFromFile.setCostPerSqFt(new BigDecimal(orderTokens[6]).setScale(2, RoundingMode.HALF_UP));
        orderFromFile.setLabourPerSqFt(new BigDecimal(orderTokens[7]).setScale(2, RoundingMode.HALF_UP));
        orderFromFile.setMaterialCost(new BigDecimal(orderTokens[8]).setScale(2, RoundingMode.HALF_UP));
        orderFromFile.setLabourCost(new BigDecimal(orderTokens[9]).setScale(2, RoundingMode.HALF_UP));
        orderFromFile.setTaxCost(new BigDecimal(orderTokens[10]).setScale(2, RoundingMode.HALF_UP));
        orderFromFile.setTotalCost(new BigDecimal(orderTokens[11]).setScale(2, RoundingMode.HALF_UP));
        return orderFromFile;
    }

    private void loadOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        Scanner scanner;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String orderDate = date.format(formatter);

        this.orders.clear();

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(fileFormat + orderDate + ".txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("-_- Could not load order file with order date.", e);
        }

        // currentLine holds most recent line read from file
        String currentLine;
        // currentOrder holds the most recent order unmarshalled
        Order currentOrder;
        // Go through file line by line, decoding each line into a 
        // Order object by calling the unmarshallOrder method
        scanner.nextLine(); //Skip header that writes out
        while (scanner.hasNextLine()) {
            // Get next line in the file
            currentLine = scanner.nextLine();
            // Unmarshall the line into a Student
            currentOrder = unmarshallOrder(currentLine);
            currentOrder.setOrderDate(date);
            // Put currentOrder into the map using orderNumber as the key
            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }
    }

    private String marshallOrder(Order order) {
        // OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,
        // LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total

        String orderAsText = order.getOrderNumber() + DELIMITER;
        orderAsText += order.getCustomerName() + DELIMITER;
        orderAsText += order.getStateAbbreviation() + DELIMITER;
        orderAsText += order.getTaxRate() + DELIMITER;
        orderAsText += order.getProductType() + DELIMITER;
        orderAsText += order.getArea() + DELIMITER;
        orderAsText += order.getCostPerSqFt() + DELIMITER;
        orderAsText += order.getLabourPerSqFt() + DELIMITER;
        orderAsText += order.getMaterialCost() + DELIMITER;
        orderAsText += order.getLabourCost() + DELIMITER;
        orderAsText += order.getTaxCost() + DELIMITER;
        orderAsText += order.getTotalCost();
        return orderAsText;
    }

    // Write all orders into order file "Orders_MMddyyyy.txt"
    private void writeOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        Scanner scanner;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String orderDate = date.format(formatter);
        String orderFileHeader = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot, "
                + "LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total";

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(fileFormat + orderDate + ".txt"));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not save order data.", e);
        }

        // Write out orderFileHeader to the order file
        out.println(orderFileHeader);
        // Write out the Order objects to the order file.
        String orderAsText;
        Set<Integer> orderNumbers = orders.keySet();
        for (Integer currentOrderNumber : orderNumbers) {
            // turn an Order into a String
            orderAsText = marshallOrder(orders.get(currentOrderNumber));
            // write the order object to the file
            out.println(orderAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        //Clean up
        out.close();
    }
}
