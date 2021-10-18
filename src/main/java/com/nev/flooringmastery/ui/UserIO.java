/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;


public interface UserIO {
    
    void print(String message);

    String readString(String prompt);
    
    String readAnswer(String prompt);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);
    
    BigDecimal readBigDecimal(String prompt);
    
    BigDecimal readBigDecimal(String prompt, BigDecimal min);
    
    LocalDate readDate(String prompt);
    
    LocalDate readFutureDate(String prompt);
    
    String readCustomerName(String prompt);
    
}
