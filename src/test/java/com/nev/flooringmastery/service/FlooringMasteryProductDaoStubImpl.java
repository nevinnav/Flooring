/**
 *
 * @author nev
 * email: nevin.natividad@gmail.com
 * date: October 8, 2021
 * purpose: Final Assessment - Flooring Mastery
 */
package com.nev.flooringmastery.service;

import com.nev.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.nev.flooringmastery.dao.FlooringMasteryProductDao;
import com.nev.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlooringMasteryProductDaoStubImpl implements FlooringMasteryProductDao {
    
    public Product onlyProduct;
    
    Map<String, Product> allProducts = new HashMap<>();
    
    @Autowired
    public FlooringMasteryProductDaoStubImpl() {
        onlyProduct = new Product();
        onlyProduct.setProductType("Carpet");
        onlyProduct.setCostPerSqFt(new BigDecimal("3.99").setScale(2, RoundingMode.HALF_UP));
        onlyProduct.setLabourPerSqFt(new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP));
    }
    
    public FlooringMasteryProductDaoStubImpl(Product product) {
        this.onlyProduct = product;
    }

    @Override
    public Product getProduct(String productType) throws FlooringMasteryPersistenceException {
        if (onlyProduct.getProductType().equals(productType)) {
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public Collection<Product> getAllProducts() throws FlooringMasteryPersistenceException {
        allProducts.put(onlyProduct.getProductType(), onlyProduct);
        return allProducts.values();
    }
    
    
    
}
