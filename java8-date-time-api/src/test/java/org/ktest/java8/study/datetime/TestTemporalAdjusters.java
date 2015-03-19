/**
 * file       : TestTemporalAdjusters.java
 * author     : Kiran Mohan
 * created on : 19-Mar-2015
 */
package org.ktest.java8.study.datetime;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

/**
 * @author Kiran Mohan
 *
 */
public class TestTemporalAdjusters {

    @Test
    public void test() {
        LocalDate firstTuesday = LocalDate.of(2000, Month.APRIL, 30)
                                          .with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.TUESDAY));
        
        System.out.println(firstTuesday.getDayOfMonth());
        assertThat(firstTuesday.getDayOfMonth(), is(4));
    }

}
