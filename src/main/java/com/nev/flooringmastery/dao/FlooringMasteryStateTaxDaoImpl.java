/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery.dao;

import com.nev.flooringmastery.dto.StateTax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class FlooringMasteryStateTaxDaoImpl implements FlooringMasteryStateTaxDao {

    public final String TAX_FILE;
    
    public FlooringMasteryStateTaxDaoImpl() {
        TAX_FILE = "Data/Taxes.txt";
    }
    
    public FlooringMasteryStateTaxDaoImpl(String stateTaxTextFile) {
        TAX_FILE = stateTaxTextFile;
    }
    
    public static final String DELIMITER = ",";
    
    private Map<String, StateTax> stateTax = new HashMap<>();
    
    private StateTax unmarshallStateTax(String stateTaxAsText) {
        // stateTaxAsText is expecting a line read in from your file.
        // state,stateName,taxRate
        // CA,California,7.25
        
        String[] stateTaxTokens = stateTaxAsText.split(DELIMITER);
        
        String stateAbbreviation = stateTaxTokens[0];
        
        // Create new StateTax object
        StateTax stateFromFile = new StateTax(stateAbbreviation);
        stateFromFile.setStateName(stateTaxTokens[1]);
        stateFromFile.setTaxRate(new BigDecimal(stateTaxTokens[2]).setScale(2, RoundingMode.HALF_UP));
        return stateFromFile;
    }
    
    private void loadTaxRates() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("-_- Could not load roster data into memory.", e);
        }
        
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStateTax holds the most recent stateTax unmarshalled
        StateTax currentStateTax;
        // Go through TAX_FILE line by line, decoding each line into a 
        // StateTax object by calling the unmarshallStateTax method
        // Process while we have more lines in the file
        scanner.nextLine(); //Skip text file header
        while (scanner.hasNextLine()){
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a StateTax
            currentStateTax = unmarshallStateTax(currentLine);
            // put currentStateTax into the map using stateAbbreviation as the key
            stateTax.put(currentStateTax.getStateAbbreviation(), currentStateTax);
        }
        //close scanner
        scanner.close();
    }
    
    
    @Override
    public StateTax getStateTax(String stateAbbreviation) throws FlooringMasteryPersistenceException {
        this.loadTaxRates();
        return stateTax.get(stateAbbreviation);
    }

    @Override
    public Collection<StateTax> getAllStateTax() throws FlooringMasteryPersistenceException {
        this.loadTaxRates();
        return stateTax.values();
    }
}
