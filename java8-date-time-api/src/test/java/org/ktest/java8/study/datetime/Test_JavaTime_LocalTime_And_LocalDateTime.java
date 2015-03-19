/**
 * file       : TestJavaTimeLocalTimeAndLocalDateTime.java
 * author     : Kiran Mohan
 * created on : 19-Mar-2015
 */
package org.ktest.java8.study.datetime;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;

import org.junit.Test;

/**
 * @author Kiran Mohan
 *
 */
public class Test_JavaTime_LocalTime_And_LocalDateTime {

    @Test
    public void testLocalTime() {
        LocalTime lunchTime = LocalTime.of(13, 0);
        System.out.println(lunchTime);
        
        LocalTime snackTime = lunchTime.plusHours(3);
        System.out.println(snackTime);
    }
    
    @Test
    public void testLocalDateTime() {
        LocalDateTime birthday = LocalDateTime.of(2000, Month.APRIL, 1, 0, 0);
        System.out.println(birthday);
        
        LocalDateTime nextBirthday = birthday.plus(Period.ofYears(1));
        System.out.println(nextBirthday);
        LocalDateTime prevBirthday = birthday.minus(Period.ofYears(1));
        System.out.println(prevBirthday);
        
    }

}
