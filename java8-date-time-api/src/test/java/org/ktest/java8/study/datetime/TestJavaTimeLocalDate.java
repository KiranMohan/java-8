/**
 * file       : TestJavaTimeLocalDate.java
 * author     : Kiran Mohan
 * created on : 19-Mar-2015
 */
package org.ktest.java8.study.datetime;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.Year;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

/**
 * @author Kiran Mohan
 *
 */
public class TestJavaTimeLocalDate {

    @Test
    public void testLocalDate() {
        LocalDate d = LocalDate.now();
        System.out.println(d);
        
        final LocalDate d1 = LocalDate.of(2000, Month.APRIL, 1);
        System.out.println(d1);
        assertTrue(d1.isLeapYear());
        
        LocalDate d2 = d1.plusYears(2);
        System.out.println(d2);
        assertEquals(2002, d2.getYear());
        
        d2 = d1.with(Year.of(2004));
        System.out.println(d2);
        assertEquals(2004, d2.getYear());
        
        LocalDate christmasDate = LocalDate.of(2000, Month.DECEMBER, 25);
        Period p = d1.until(christmasDate);
        System.out.printf("period %d years, %d months, %d days\n", p.getYears(), p.getMonths(), p.getDays());
        
        long daysUntilChristmas = d1.until(christmasDate, ChronoUnit.DAYS);
        assertThat(daysUntilChristmas,is(268l));
    }
    
    @Test
    public void test_PartialDates() {
        MonthDay christmasDay = MonthDay.of(Month.DECEMBER, 25);
        System.out.println(christmasDay);
        
        Year milleniumYear = Year.of(2000);
        System.out.println("millenium year : " + milleniumYear );
    }
    

}
