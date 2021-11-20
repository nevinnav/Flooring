/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery;

import com.nev.flooringmastery.controller.FlooringMasteryController;
import com.nev.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.nev.flooringmastery.service.FlooringMasteryDataValidationException;
import com.nev.flooringmastery.service.FlooringMasteryNoOrderException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    
    public static void main(String[] args) throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException, FlooringMasteryNoOrderException {
        
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.nev.flooringmastery");
        appContext.refresh();
        
        FlooringMasteryController controller = appContext.getBean("flooringMasteryController", 
                FlooringMasteryController.class);
        controller.run();
        
        /*FlooringMasteryOrderDao orderDao = new FlooringMasteryOrderDaoImpl();
        FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoImpl();
        FlooringMasteryStateTaxDao stateTaxDao = new FlooringMasteryStateTaxDaoImpl();
        FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoImpl();
        FlooringMasteryServiceLayer service = new FlooringMasteryServiceLayerImpl(orderDao, productDao, stateTaxDao, auditDao);
        UserIO userIO = new UserIOConsoleImpl();
        FlooringMasteryView view = new FlooringMasteryView(userIO);
        FlooringMasteryController controller = new FlooringMasteryController(service, view);
        controller.run();
        **/
    }
}
