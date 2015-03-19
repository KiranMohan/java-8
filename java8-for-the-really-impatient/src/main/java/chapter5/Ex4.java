/**
 * file       : Ex4.java
 * author     : "Kiran Mohan"
 * created on : Mar 19, 2015
 */
package chapter5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;

/**
 * Write an equivalent of the Unix cal program that displays a calendar for a month. 
 * For example, java Cal 3 2013 should display
                 1  2  3
     4  5  6  7  8  9 10
    11 12 13 14 15 16 17
    18 19 20 21 22 23 24
    25 26 27 28 29 30 31

   indicating that March 1 is a Friday. (Show the weekend at the end of the week.)
 * @author "Kiran Mohan"
 *
 */
public class Ex4 {
    
    public static void printCalendarMonth(Month month, int year) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());
        
        StringBuilder calendar = new StringBuilder();
        calendar.append(month.toString() + ", " + year + "\n");
        int dayOfWeek = DayOfWeek.MONDAY.getValue();
        for (; dayOfWeek < firstDayOfMonth.getDayOfWeek().getValue()  ; ++dayOfWeek){
            calendar.append("   ");
        }
        
        for (int i = 1; i <= lastDayOfMonth.getDayOfMonth(); ++i) {
            if (dayOfWeek == DayOfWeek.SUNDAY.getValue() ) {
                calendar.append(String.format("%2d\n", i));
                dayOfWeek = DayOfWeek.MONDAY.getValue();
            } else {
                calendar.append(String.format("%2d ", i));
                ++dayOfWeek;
            }
        }
        if ( calendar.charAt(calendar.length()-1) != '\n' ) {
            calendar.append("\n");
        }
        
        System.out.println(calendar);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        printCalendarMonth(Month.APRIL, 2000);
        printCalendarMonth(Month.APRIL, 2001);
        printCalendarMonth(Month.MAY, 2000);
        printCalendarMonth(Month.FEBRUARY, 2000);
        
    }

}
