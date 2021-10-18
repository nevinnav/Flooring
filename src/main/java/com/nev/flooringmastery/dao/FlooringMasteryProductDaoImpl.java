/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery.dao;

import com.nev.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class FlooringMasteryProductDaoImpl implements FlooringMasteryProductDao {

    public final String PRODUCT_FILE;
    
    public FlooringMasteryProductDaoImpl() {
        PRODUCT_FILE = "Data/Products.txt";
    }
    
    public FlooringMasteryProductDaoImpl(String productTextFile) {
        PRODUCT_FILE = productTextFile;
    }
    
    public static final String DELIMITER = ",";
    
    private Map<String, Product> products = new HashMap<>();
    
    private Product unmarshallProduct(String productAsText) {
        // productAsText is expecting a line read in from your file.
        // productType,materialCost,labourCost
        // Hardwood,12.49,9.25
        
        String[] productTokens = productAsText.split(DELIMITER);
        String productType = productTokens[0];
        
        // Create new Product object
        Product productFromFile = new Product(productType);
        productFromFile.setCostPerSqFt(new BigDecimal(productTokens[1]).setScale(2, RoundingMode.HALF_UP));
        productFromFile.setLabourPerSqFt(new BigDecimal(productTokens[2]).setScale(2, RoundingMode.HALF_UP));
        return productFromFile;
    }
    
    private void loadProducts() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        try {
            //Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("-_- Could not load product data into memory.", e);
        }
        
        // currentLine holes the most recent line read from the file
        String currentLine;
        // currentProduct holds the most recent product unmarshalled
        Product currentProduct;
        // Go through PRODUCT_FILE line by line, decoding each line into a 
        // Product object by calling the unmarshallProduct method.
        // Producess whil we have more lines in the file
        scanner.nextLine(); // Skip text file header
        while(scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a product
            currentProduct = unmarshallProduct(currentLine);
            // put currentProduct in the map using productType as the key
            products.put(currentProduct.getProductType(), currentProduct);
        }
        // close scanner
        scanner.close();
    }
    
    @Override
    public Product getProduct(String productType) throws FlooringMasteryPersistenceException {
        this.loadProducts();
        return products.get(productType);
    }

    @Override
    public Collection<Product> getAllProducts() throws FlooringMasteryPersistenceException {
        this.loadProducts();
        return products.values();
    }
    
}