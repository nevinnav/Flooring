/**
 *
 * @author nev
 * email: nevin.natividad@gmail.com
 * date: October 8, 2021
 * purpose: Final Assessment - Flooring Mastery
 */
package com.nev.flooringmastery.service;

import com.nev.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.nev.flooringmastery.dao.FlooringMasteryStateTaxDao;
import com.nev.flooringmastery.dto.StateTax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlooringMasteryStateTaxDaoStubImpl implements FlooringMasteryStateTaxDao {

public StateTax onlyState;

Map<String, StateTax> allStateTax = new HashMap<>();

@Autowired
public FlooringMasteryStateTaxDaoStubImpl() {
    onlyState = new StateTax();
    onlyState.setStateAbbreviation("CA");
    onlyState.setStateName("California");
    onlyState.setTaxRate(new BigDecimal("7.25").setScale(2, RoundingMode.HALF_UP));
}

public FlooringMasteryStateTaxDaoStubImpl(StateTax state) {
    this.onlyState = state;
}

    @Override
    public StateTax getStateTax(String stateAbbreviation) throws FlooringMasteryPersistenceException {
        if (onlyState.getStateAbbreviation().equals(stateAbbreviation)) {
            return onlyState;
        } else {
            return null;
        }
    }

    @Override
    public Collection<StateTax> getAllStateTax() throws FlooringMasteryPersistenceException {
        allStateTax.put(onlyState.getStateAbbreviation(), onlyState);
        return allStateTax.values();
    }

}
