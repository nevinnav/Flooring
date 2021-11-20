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


public class Product {
    
    private String productType;
    private BigDecimal costPerSqFt;
    private BigDecimal labourPerSqFt;
    
    public Product() {
        
    }
    
    public Product(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.productType);
        hash = 71 * hash + Objects.hashCode(this.costPerSqFt);
        hash = 71 * hash + Objects.hashCode(this.labourPerSqFt);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.costPerSqFt, other.costPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.labourPerSqFt, other.labourPerSqFt)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "productType=" + productType + ", costPerSqFt=" + costPerSqFt + ", labourPerSqFt=" + labourPerSqFt + '}';
    }
    
}
