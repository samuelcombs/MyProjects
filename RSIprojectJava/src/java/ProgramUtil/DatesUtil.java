/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramUtil;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Tyler Jurczak
 */
public class DatesUtil {
    
    public static String convertYearsToMonths(int years, int months) {
        String monthCount = "0";
        int monthsInt = Integer.parseInt(monthCount);
        if (years > 0) {
            monthsInt = years * 12;
        }
        monthCount += monthsInt + months;

        return monthCount;
    }

    public static int convertMonthstoYears(int months) {
        int yearCount = 0;
        if (months > 0) {
            yearCount = months / 12;
        }
        return yearCount;
    }

    public static int convertLeftOverMonths(int months) {
        int remainingMonths = 0;
        if (months > 0) {
            remainingMonths = months % 12;
        }
        return remainingMonths;
    }

    public static String convertDateToSQL(String date) {
        String SQLDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
        return SQLDate;
    }

    public static int convertDateToMonths(String date) {
        LocalDate startDate = LocalDate.parse(date);
        LocalDate currentDate = LocalDate.now();
        try {
            int months = (int) ChronoUnit.MONTHS.between(startDate, currentDate);
            return months;
        } catch (DateTimeException e) {
            return 0;
        }
    }
    
    public static String convertMonthsToNewDate(int months){
        String newDate = "";
        LocalDate currentDate = LocalDate.now();
        try {
            LocalDate newLocal = currentDate.minusMonths(months);
            newDate += newLocal;
        return newDate;
        } catch (DateTimeException e) {
            return null;
        }
    }

    public static String convertDateToReadable(String dateAdded) {
        LocalDate formattedDate = LocalDate.parse(dateAdded);
        String readableDate; 
        readableDate = new SimpleDateFormat("MMM,  'yyyy'").format(formattedDate);
        return readableDate;
    }
    
}
