/**
 * file       : Ex11.java
 * author     : "Kiran Mohan"
 * created on : Mar 21, 2015
 */
package chapter5;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Your return flight leaves Frankfurt at 14:05 and arrives in Los Angeles at
 * 16:40. How long is the flight? Write a program that can handle calculations
 * like this
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex11 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Frankfurt TimeZone CET, 14:05 departure
        LocalDateTime departure = LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 5));
        // Los Angeles America/Los_Angeles, arrival 16:40
        LocalDateTime arrival =   LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 40));
        
        Duration duration = getDuration(departure, ZoneId.of("Europe/Berlin"), 
                                        arrival,   ZoneId.of("America/Los_Angeles"));
        System.out.println("Duration - " + duration.toString());
        System.out.println("Duration - " + duration.toHours() + "Hrs " + duration.toMinutes()%60 + "Mins");
    }

    /**
     * 
     * @param departure
     * @param departureZoneId
     * @param arrival
     * @param arrivalZoneId
     * @return
     */
    public static Duration getDuration(LocalDateTime departure, ZoneId departureZoneId,
                                       LocalDateTime arrival,   ZoneId arrivalZoneId) {
        return getDuration(ZonedDateTime.of(departure, departureZoneId), ZonedDateTime.of(arrival, arrivalZoneId));
    }
    
    /**
     * @param departure
     * @param arrival
     * @return
     */
    public static Duration getDuration(ZonedDateTime departure, ZonedDateTime arrival) {
        long durationMinutes = departure.until(arrival, ChronoUnit.MINUTES);
        Duration duration = Duration.of(durationMinutes, ChronoUnit.MINUTES);
        return duration;
    }
    

}
