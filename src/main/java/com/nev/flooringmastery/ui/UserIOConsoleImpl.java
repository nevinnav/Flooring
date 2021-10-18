/**
*
* @author nev
* email: nevin.natividad@gmail.com
* date: October 8, 2021
* purpose: Final Assessment - Flooring Mastery
*/
package com.nev.flooringmastery.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
    
    Scanner inputReader = new Scanner(System.in);
    
    @Override
    public void print(String message) {
        System.out.println(message);
    }
    
    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return inputReader.nextLine();
    }
    
    @Override
    public String readAnswer(String prompt) {
        boolean isValid = false;
        String input = null;
        
        do {
        
            System.out.println(prompt);
            input = inputReader.nextLine();
            
            if (input == null || input.isEmpty()) {
                System.out.println("Please enter information.");
            } else {
                isValid = true;
            }
        } while(!isValid);
        
        return input;
    }
    
    @Override
    public int readInt(String prompt) {
        boolean invalidInput = true;
        int int1 = 0;
        while (invalidInput) {
            try {
                // print the message msgPrompt (ex: asking for the # of cats!)
                System.out.println(prompt);
                // Get the input line, and try and parse
                int1 = Integer.parseInt(inputReader.nextLine()); // if it's 'bob' it'll break
                invalidInput = false; // or you can use 'break;'
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                System.out.println("Input error. Please try again.");
            }
        }
        return int1;  
    }
    
    @Override
    public int readInt(String prompt, int min, int max) {
        int result;
        do {
            result = readInt(prompt);
        } while (result < min || result > max);

        return result;

    }
        
    @Override
    public double readDouble(String prompt) {
        boolean invalidInput = true;
        double double1 = 0;
        while (invalidInput) {
            try {
                // print the message msgPrompt (ex: asking for the # of cats!)
                System.out.println(prompt);
                // Get the input line, and try and parse
                double1 = Double.parseDouble(inputReader.nextLine()); // if it's 'bob' it'll break
                invalidInput = false; // or you can use 'break;'
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                System.out.println("Input error. Please try again.");
            }
        }
        
        return double1;  
    }
        
    @Override
    public double readDouble(String prompt, double min, double max) {
        double result;
        do {
            result = readDouble(prompt);
        } while (result < min || result > max);
        return result;
    }

    @Override
    public float readFloat(String prompt) {
        boolean invalidInput = true;
        float float1 = 0;
        while (invalidInput) {
            try {
                // print the message msgPrompt (ex: asking for the # of cats!)
                System.out.println(prompt);
                // Get the input line, and try and parse
                float1 = Float.parseFloat(inputReader.nextLine()); // if it's 'bob' it'll break
                invalidInput = false; // or you can use 'break;'
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                System.out.println("Input error. Please try again.");
            }
        }
        return float1;  
    }    
        
        

    @Override
    public float readFloat(String prompt, float min, float max) {
        float result;
        do {
            result = readFloat(prompt);
        } while (result < min || result > max);

        return result;
    }

    @Override
    public long readLong(String prompt) {
        boolean invalidInput = true;
        long long1 = 0;
        while (invalidInput) {
            try {
                // print the message msgPrompt (ex: asking for the # of cats!)
                System.out.println(prompt);
                // Get the input line, and try and parse
                long1 = Long.parseLong(inputReader.nextLine()); // if it's 'bob' it'll break
                invalidInput = false; // or you can use 'break;'
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                System.out.println("Input error. Please try again.");
            }
        }
        
        return long1; 
    }    

    @Override
    public long readLong(String prompt, long min, long max) {
        long result;
        do {
            result = readLong(prompt);
        } while (result < min || result > max);

        return result;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        BigDecimal result = null;
        String valueString;
        boolean invalidInput = true;
        while (invalidInput) {
            try {
                valueString = readString(prompt);
                result = new BigDecimal(valueString).setScale(2, RoundingMode.HALF_UP);
                invalidInput = false;
            } catch (NumberFormatException e) {
                System.out.println("Input Error. Please try again.");
            }
        }
        return result;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min) {
        BigDecimal result = null;
        String valueString;
        boolean invalidInput = true;
        boolean invalidRange = true;
        while (invalidInput) {
            try {
                do {
                    valueString = readString(prompt);
                    result = new BigDecimal(valueString).setScale(2, RoundingMode.HALF_UP);
                    invalidInput = false;
                    if (result.compareTo(min) >= 0) {
                        invalidRange = false;
                    } else {
                        this.print("Value must be greater than or equal to " + min);
                    }
                } while (invalidRange);
            } catch (NumberFormatException e) {
                this.print("Invalid value");
            }
        }
        return result;
    }

    @Override
    public LocalDate readDate(String prompt) {
        LocalDate date = null;
        String valueString;
        boolean invalidDate = true;
        
        do {
            try {
                valueString = this.readString(prompt);
                date = LocalDate.parse(valueString);
                invalidDate = false;
            } catch (DateTimeParseException e) {
                this.print("Invalid date");
            }
        } while (invalidDate);
        return date;
    }

    @Override
    public LocalDate readFutureDate(String prompt) {
        LocalDate date = null;
        String valueString;
        boolean invalidDate = true;
        
        do {
            try {
                valueString = this.readString(prompt);
                date = LocalDate.parse(valueString);
                if (date.isAfter(LocalDate.now())) {
                    invalidDate = false;
                } else {
                    this.print("Date is not in future");
                }
            } catch (DateTimeParseException e) {
                this.print("Invalid date");
            }
        } while (invalidDate);
        return date;
    }

    @Override
    public String readCustomerName(String prompt) {
        String customerName = null;

        do {
                customerName = this.readString(prompt);
            } while (customerName.isBlank() || !customerName.matches("^[A-Za-z0-9., ]+$"));
        
        return customerName;
        }
    
}