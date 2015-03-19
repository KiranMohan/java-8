/**
 * file       : Ex3.java
 * author     : "Kiran Mohan"
 * created on : Mar 19, 2015
 */
package chapter5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;

/**
 * Implement a method next that takes a Predicate<LocalDate> and returns an
 * adjuster yielding the next date fulfilling the predicate. For example,
 * today.with(next(w -> getDayOfWeek().getValue() < 6))
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex3 {
    
    public static TemporalAdjuster next(Predicate<LocalDate> condition) {
        return TemporalAdjusters.ofDateAdjuster(w -> {
           do {
               w = w.plusDays(1);
           } while (!condition.test(w));
           return w;
        });
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2105, Month.MARCH, 19); // thursday
        LocalDate nextDate = date.with(next(w -> 
        w.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) < 0));
        System.out.println(nextDate);
        
        date = LocalDate.of(2105, Month.MARCH, 20); // friday
        nextDate = date.with(next(w -> 
        w.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) < 0));
        System.out.println(nextDate);
    }

}
