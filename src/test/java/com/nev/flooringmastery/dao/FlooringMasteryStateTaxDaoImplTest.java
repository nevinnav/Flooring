/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery.dao;

import com.nev.flooringmastery.dto.StateTax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class FlooringMasteryStateTaxDaoImplTest {
  
    FlooringMasteryStateTaxDao testStateDao;
    
    public FlooringMasteryStateTaxDaoImplTest() {
    }
    
    @BeforeEach
    public void setUp() {
        testStateDao = new FlooringMasteryStateTaxDaoImpl("Test/testTaxes.txt");
    }  

    @Test
    public void testGetState() throws FlooringMasteryPersistenceException {
        StateTax testState = testStateDao.getStateTax("CA");
        
        //Check specifics such as StateName and TaxRate
        assertEquals(testState.getStateName(), "California", "State name should be California");
        assertEquals(testState.getTaxRate(), new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
        
    }
    @Test
    public void testGetAllStates() throws FlooringMasteryPersistenceException {
        Collection<StateTax> allTestStates = testStateDao.getAllStateTax();
        
        //Check specifics
        assertNotNull(allTestStates, "All state tax list should NOT be null.");
        assertEquals(10, allTestStates.size(), "State tax list should include 10 states.");
        assertFalse(allTestStates.contains(testStateDao.getStateTax("NY")), "State tax list should not include New York (NY)");
        assertTrue(allTestStates.contains(testStateDao.getStateTax("MD")), "State tax list should include Maryland (MD)");
        
    }

}
