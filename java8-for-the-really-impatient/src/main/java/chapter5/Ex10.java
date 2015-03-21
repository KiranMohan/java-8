/**
 * file       : Ex10.java
 * author     : "Kiran Mohan"
 * created on : Mar 21, 2015
 */
package chapter5;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Your flight from Los Angeles to Frankfurt leaves at 3:05 pm local time and
 * takes 10 hours and 50 minutes. When does it arrive? Write a program that can
 * handle calculations like this.
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex10 {

    public static ZonedDateTime getArrivalLocalTime(ZonedDateTime departure, 
                                             ZoneId destinationZone, 
                                             Duration duration) {
        return departure.plus(duration)
                        .withZoneSameInstant(destinationZone);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        // Los Angeles 3.05 pm America/Los_Angeles
        ZonedDateTime departure = ZonedDateTime.of(LocalDate.now(), 
                                                   LocalTime.of(15, 5),
                                                   ZoneId.of("America/Los_Angeles"));

        // Frankfurt TimeZone CET, flight duration 10hrs 50 mins
        ZonedDateTime arrival = getArrivalLocalTime(departure, 
                                                    ZoneId.of("Europe/Berlin"),
                                                    Duration.of(10 * 60 + 50, ChronoUnit.MINUTES));

        System.out.println("Arrival time: " + arrival);

    }

}
