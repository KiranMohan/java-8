/**
 * file       : Ex1.java
 * author     : "Kiran Mohan"
 * created on : Mar 19, 2015
 */
package chapter5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/**
 * Compute Programmer’s Day without using plusDays.
 * @author "Kiran Mohan"
 *
 */
public class Ex1 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        LocalDate programmersDay = LocalDate.of(2014, 1, 1).with(ChronoField.DAY_OF_YEAR, 256);
        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE.format(programmersDay));
        
        programmersDay = LocalDate.of(2016, 1, 1).with(ChronoField.DAY_OF_YEAR, 256);
        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE.format(programmersDay));
    }

}
