/**
 * file       : TestJavaTimeDateFormatter.java
 * author     : Kiran Mohan
 * created on : 19-Mar-2015
 */
package org.ktest.java8.study.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.junit.Test;

/**
 * @author Kiran Mohan
 *
 */
public class TestJavaTimeDateFormatter {

    @Test
    public void testStandardFormatters() {
        LocalDateTime birthday = LocalDateTime.of(2000, Month.APRIL, 1, 0, 0);
        ZonedDateTime birthdayZoned = ZonedDateTime.of(birthday, ZoneId.of("Asia/Kolkata"));
                
        
        System.out.println("BASIC_ISO_DATE       :" + DateTimeFormatter.BASIC_ISO_DATE.format(birthdayZoned));
        
        System.out.println("ISO_DATE             :" + DateTimeFormatter.ISO_DATE.format(birthdayZoned));
        System.out.println("ISO_TIME             :" + DateTimeFormatter.ISO_TIME.format(birthdayZoned));
        System.out.println("ISO_DATE_TIME        :" + DateTimeFormatter.ISO_DATE_TIME.format(birthdayZoned));
        
        System.out.println("ISO_OFFSET_DATE      :" + DateTimeFormatter.ISO_OFFSET_DATE.format(birthdayZoned));
        System.out.println("ISO_OFFSET_TIME      :" + DateTimeFormatter.ISO_OFFSET_TIME.format(birthdayZoned));
        System.out.println("ISO_OFFSET_DATE_TIME :" + DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(birthdayZoned));
        
        System.out.println("ISO_ZONED_DATE_TIME  :" + DateTimeFormatter.ISO_ZONED_DATE_TIME.format(birthdayZoned));
        
        System.out.println("ISO_INSTANT          :" + DateTimeFormatter.ISO_INSTANT.format(birthdayZoned));
        
        System.out.println("ISO_DATE zoned       :" + DateTimeFormatter.ISO_DATE.format(birthdayZoned));
        System.out.println("ISO_TIME zoned       :" + DateTimeFormatter.ISO_TIME.format(birthdayZoned));
        System.out.println("ISO_DATE_TIME zoned  :" + DateTimeFormatter.ISO_DATE_TIME.format(birthdayZoned));
        System.out.println("ISO_DATE             :" + DateTimeFormatter.ISO_DATE.format(birthday));
        System.out.println("ISO_TIME             :" + DateTimeFormatter.ISO_TIME.format(birthday));
        System.out.println("ISO_DATE_TIME        :" + DateTimeFormatter.ISO_DATE_TIME.format(birthday));
        
        System.out.println("RFC_1123_DATE_TIME   :" + DateTimeFormatter.RFC_1123_DATE_TIME.format(birthdayZoned));
    }
    
    @Test
    public void testLocaleSpecificFormatters() {
        Locale hi = new Locale("hi", "IN");
        LocalDateTime birthday = LocalDateTime.of(2000, Month.APRIL, 1, 0, 0);
        ZonedDateTime birthdayZoned = ZonedDateTime.of(birthday, ZoneId.of("Asia/Kolkata"));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT); 
        System.out.println(formatter.format(birthdayZoned));
        System.out.println(formatter.withLocale(hi).format(birthdayZoned));
        System.out.println(formatter.withLocale(Locale.FRENCH).format(birthdayZoned));
        
        formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM); 
        System.out.println(formatter.format(birthdayZoned));
        System.out.println(formatter.withLocale(hi).format(birthdayZoned));
        System.out.println(formatter.withLocale(Locale.FRENCH).format(birthdayZoned));
        
        formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG); 
        System.out.println(formatter.format(birthdayZoned));
        System.out.println(formatter.withLocale(hi).format(birthdayZoned));
        System.out.println(formatter.withLocale(Locale.FRENCH).format(birthdayZoned));
        
        formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL); 
        System.out.println(formatter.format(birthdayZoned));
        System.out.println(formatter.withLocale(hi).format(birthdayZoned));
        System.out.println(formatter.withLocale(Locale.FRENCH).format(birthdayZoned));
        
    }
    
    @Test
    public void testPatternSpecificFormatter() {
        LocalDateTime birthday = LocalDateTime.of(2000, Month.APRIL, 1, 0, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");
        System.out.println(formatter.format(birthday));
    }
    
    @Test
    public void testParseDate() {
        LocalDate birthday = LocalDate.parse("2000-04-01");
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(birthday));
        
        birthday = LocalDate.parse("01 April, 2000", DateTimeFormatter.ofPattern("dd MMMM, yyyy"));
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(birthday));
    }

}
