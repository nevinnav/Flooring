/**
 *
 * @author nev
 * email: nevin.natividad@gmail.com
 * date: October 8, 2021
 * purpose: Final Assessment - Flooring Mastery
 */
package com.nev.flooringmastery.service;

import com.nev.flooringmastery.dao.FlooringMasteryAuditDao;
import com.nev.flooringmastery.dao.FlooringMasteryPersistenceException;
import org.springframework.stereotype.Component;

@Component
public class FlooringMasteryAuditDaoStubImpl implements FlooringMasteryAuditDao {
    
    @Override
    public void writeAuditEntry(String entry) throws FlooringMasteryPersistenceException {
        //do nothing...
    }
}
