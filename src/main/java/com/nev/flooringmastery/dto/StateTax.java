/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class StateTax {
    
    private String stateAbbreviation;
    private String stateName;
    private BigDecimal taxRate;

    public StateTax(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }
    
    public StateTax(String stateAbbreviation, String stateName, BigDecimal taxRate) {
        this.stateAbbreviation = stateAbbreviation;
        this.stateName = stateName;
        this.taxRate = taxRate.setScale(2, RoundingMode.HALF_UP);
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }
    
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }
    
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
}
