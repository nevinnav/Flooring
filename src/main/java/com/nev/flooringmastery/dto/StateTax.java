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
import java.util.Objects;


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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.stateAbbreviation);
        hash = 67 * hash + Objects.hashCode(this.stateName);
        hash = 67 * hash + Objects.hashCode(this.taxRate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StateTax other = (StateTax) obj;
        if (!Objects.equals(this.stateAbbreviation, other.stateAbbreviation)) {
            return false;
        }
        if (!Objects.equals(this.stateName, other.stateName)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StateTax{" + "stateAbbreviation=" + stateAbbreviation + ", stateName=" + stateName + ", taxRate=" + taxRate + '}';
    }
    
}
