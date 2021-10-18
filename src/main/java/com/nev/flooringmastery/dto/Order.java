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


public class Order {
    
    private LocalDate orderDate;           
    private int orderNumber;
    private String customerName;
    private String stateAbbreviation;
    private BigDecimal taxRate;         //Remove from Order?? StateTax
    private String productType;         //Remove from Order?? Product
    private BigDecimal area;
    private BigDecimal costPerSqFt;     //Remove from Order?? Product
    private BigDecimal labourPerSqFt;   //Remove from Order?? Product
    private BigDecimal materialCost;    //Remove from Order?? Product
    private BigDecimal labourCost;      //Calculation
    private BigDecimal taxCost;         //Calculation
    private BigDecimal totalCost;       //Calculation

    //public Order (int orderNumber, StateTax stateAbbreviation, Product productType, BigDecimal area) {
    //
    //}

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
    
}
