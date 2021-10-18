/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery;

import com.nev.flooringmastery.controller.FlooringMasteryController;
import com.nev.flooringmastery.dao.FlooringMasteryOrderDao;
import com.nev.flooringmastery.dao.FlooringMasteryOrderDaoImpl;
import com.nev.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.nev.flooringmastery.dao.FlooringMasteryProductDao;
import com.nev.flooringmastery.dao.FlooringMasteryProductDaoImpl;
import com.nev.flooringmastery.dao.FlooringMasteryStateTaxDao;
import com.nev.flooringmastery.dao.FlooringMasteryStateTaxDaoImpl;
import com.nev.flooringmastery.service.FlooringMasteryDataValidationException;
import com.nev.flooringmastery.service.FlooringMasteryNoOrderException;
import com.nev.flooringmastery.service.FlooringMasteryServiceLayer;
import com.nev.flooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.nev.flooringmastery.ui.FlooringMasteryView;
import com.nev.flooringmastery.ui.UserIO;
import com.nev.flooringmastery.ui.UserIOConsoleImpl;


public class App {
    
    public static void main(String[] args) throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException, FlooringMasteryNoOrderException {
        FlooringMasteryOrderDao orderDao = new FlooringMasteryOrderDaoImpl();
        FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoImpl();
        FlooringMasteryStateTaxDao stateTaxDao = new FlooringMasteryStateTaxDaoImpl();
        FlooringMasteryServiceLayer service = new FlooringMasteryServiceLayerImpl(orderDao, productDao, stateTaxDao);
        UserIO userIO = new UserIOConsoleImpl();
        FlooringMasteryView view = new FlooringMasteryView(userIO);
        FlooringMasteryController controller = new FlooringMasteryController(service, view);
        controller.run();
    }
}
