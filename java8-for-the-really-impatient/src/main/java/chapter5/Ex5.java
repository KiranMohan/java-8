/**
 * file       : Ex5.java
 * author     : Kiran Mohan
 * created on : 20-Mar-2015
 */
package chapter5;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * Write a program that prints how many days you have been alive.
 * @author Kiran Mohan
 *
 */
public class Ex5 {
    
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2000, Month.APRIL, 1);
        System.out.println("number of days alive : " + date.until(LocalDate.now(), ChronoUnit.DAYS));
    }

}
