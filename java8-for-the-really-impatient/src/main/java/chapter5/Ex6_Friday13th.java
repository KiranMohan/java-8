/**
 * file       : Ex6_Friday13th.java
 * author     : Kiran Mohan
 * created on : 20-Mar-2015
 */
package chapter5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

/**
 * @author Kiran Mohan
 *
 */
public class Ex6_Friday13th {

    /**
     * @param args
     */
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(1900, Month.JANUARY,13);
        
//        Stream.iterate(date, d -> d.plus(Period.ofMonths(1)))
//              .limit(date.until(LocalDate.of(1999, Month.DECEMBER, 13), ChronoUnit.MONTHS))
//              .filter(d -> d.getDayOfWeek() == DayOfWeek.FRIDAY)
//              .forEach(System.out::println);
        
        while(date.getYear() < 2000) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                System.out.println(date);
            }
            date = date.plusMonths(1);
        }
    }

}
