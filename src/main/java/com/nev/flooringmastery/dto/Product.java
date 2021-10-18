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


public class Product {
    
    private String productType;
    private BigDecimal costPerSqFt;
    private BigDecimal labourPerSqFt;
    
    public Product(String productType) {
        this.productType = productType;
    }

    public Product(String productType, BigDecimal costPerSqFt, BigDecimal labourPerSqFt) {
        this.productType = productType;
        this.costPerSqFt = costPerSqFt.setScale(2, RoundingMode.HALF_UP);
        this.labourPerSqFt = labourPerSqFt.setScale(2, RoundingMode.HALF_UP);
    }

    public String getProductType() {
        return productType;
    }
    
    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public BigDecimal getLabourPerSqFt() {
        return labourPerSqFt;
    }

    public void setLabourPerSqFt(BigDecimal labourPerSqFt) {
        this.labourPerSqFt = labourPerSqFt;
    }
}
