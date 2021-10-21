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
import java.time.LocalDate;
import java.util.Objects;


public class Order {
    
    private LocalDate orderDate;           
    private int orderNumber;
    private String customerName;
    private String stateAbbreviation;
    private BigDecimal taxRate;   
    private String productType;        
    private BigDecimal area;
    private BigDecimal costPerSqFt;    
    private BigDecimal labourPerSqFt;  
    private BigDecimal materialCost;    
    private BigDecimal labourCost;    
    private BigDecimal taxCost;        
    private BigDecimal totalCost;     


    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getLabourPerSqFt() {
        return labourPerSqFt;
    }

    public void setLabourPerSqFt(BigDecimal labourPerSqFt) {
        this.labourPerSqFt = labourPerSqFt.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getMaterialCost() {
        return this.materialCost;
    }

    public void setMaterialCost() {
        this.materialCost = this.area.multiply(this.costPerSqFt).setScale(2, RoundingMode.HALF_UP);
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }
    
    public BigDecimal getLabourCost() {
        return labourCost;
    }

    public void setLabourCost() {
        this.labourCost = this.area.multiply(this.labourPerSqFt).setScale(2, RoundingMode.HALF_UP);
    }
    
    public void setLabourCost(BigDecimal labourCost) {
        this.labourCost = labourCost;
    }

    public BigDecimal getTaxCost() {
        return taxCost;
    }

    public void setTaxCost() {
        this.taxCost = this.taxRate.divide(new BigDecimal("100")).multiply(this.materialCost.add(this.labourCost)).setScale(2, RoundingMode.HALF_UP);
    }
    
    public void setTaxCost(BigDecimal taxCost) {
        this.taxCost = taxCost;
    }

    public BigDecimal getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost() {
        this.totalCost = this.materialCost.add(this.labourCost).add(this.taxCost).setScale(2, RoundingMode.HALF_UP);
    }
    
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.orderDate);
        hash = 29 * hash + this.orderNumber;
        hash = 29 * hash + Objects.hashCode(this.customerName);
        hash = 29 * hash + Objects.hashCode(this.stateAbbreviation);
        hash = 29 * hash + Objects.hashCode(this.taxRate);
        hash = 29 * hash + Objects.hashCode(this.productType);
        hash = 29 * hash + Objects.hashCode(this.area);
        hash = 29 * hash + Objects.hashCode(this.costPerSqFt);
        hash = 29 * hash + Objects.hashCode(this.labourPerSqFt);
        hash = 29 * hash + Objects.hashCode(this.materialCost);
        hash = 29 * hash + Objects.hashCode(this.labourCost);
        hash = 29 * hash + Objects.hashCode(this.taxCost);
        hash = 29 * hash + Objects.hashCode(this.totalCost);
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
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.stateAbbreviation, other.stateAbbreviation)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.costPerSqFt, other.costPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.labourPerSqFt, other.labourPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.labourCost, other.labourCost)) {
            return false;
        }
        if (!Objects.equals(this.taxCost, other.taxCost)) {
            return false;
        }
        if (!Objects.equals(this.totalCost, other.totalCost)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "orderDate=" + orderDate + ", orderNumber=" + orderNumber + ", customerName=" + customerName + ", stateAbbreviation=" + stateAbbreviation + ", taxRate=" + taxRate + ", productType=" + productType + ", area=" + area + ", costPerSqFt=" + costPerSqFt + ", labourPerSqFt=" + labourPerSqFt + ", materialCost=" + materialCost + ", labourCost=" + labourCost + ", taxCost=" + taxCost + ", totalCost=" + totalCost + '}';
    }
    
}
