/**
 * file       : Ex2.java
 * author     : "Kiran Mohan"
 * created on : Mar 19, 2015
 */
package chapter5;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * What happens when you add one year to LocalDate.of(2000, 2, 29)? Four years? Four times one year?
 * @author "Kiran Mohan"
 *
 */
public class Ex2 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        
        LocalDate origDate = LocalDate.of(2000, 2, 29);
        
        LocalDate date = origDate.plusYears(1);
        System.out.println(formatter.format(date));
        date = origDate.plusYears(4);
        System.out.println(formatter.format(date));
        
        System.out.println();
        date = origDate.plus(Period.ofYears(1));
        System.out.println(formatter.format(date));
        date = origDate.plus(Period.ofYears(4));
        System.out.println(formatter.format(date));
    }

}
