/**
 * file       : Ex12.java
 * author     : "Kiran Mohan"
 * created on : Mar 21, 2015
 */
package chapter5;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Write a program that solves the problem described at the beginning of Section
 * 5.5, “Zoned Time,” on page 109. Read a set of appointments in different time
 * zones and alert the user which ones are due within the next hour in local
 * time.
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex12 {
    
    private static LocalDateTime now = LocalDateTime.now();;

    public static boolean alert(LocalDateTime appointment, ZoneId id) {
        return alert(appointment.atZone(id));
    }
    
    public static boolean alert(ZonedDateTime appointment) {
       
        long timeUntil = now.until(appointment, ChronoUnit.MINUTES);
        return timeUntil < 60;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        LocalDateTime appointment1 = now.plusMinutes(70);
        LocalDateTime appointment2 = now.plusMinutes(60);
        LocalDateTime appointment3 = now.plusMinutes(50);
        
        System.out.println(alert(appointment1, ZoneId.of("America/Los_Angeles")));
        System.out.println(alert(appointment2, ZoneId.of("America/Los_Angeles")));
        System.out.println(alert(appointment3, ZoneId.of("America/Los_Angeles")));
    }

}
