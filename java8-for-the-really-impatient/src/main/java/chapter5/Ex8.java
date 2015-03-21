/**
 * file       : Ex8.java
 * author     : "Kiran Mohan"
 * created on : Mar 21, 2015
 */
package chapter5;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Obtain the offsets of today’s date in all supported time zones for the
 * current time instant, turning ZoneId.getAvailableZoneIds into a stream and
 * using stream operations.
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex8 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        ZoneId.getAvailableZoneIds()
              .stream()
              .map( zoneId -> now.withZoneSameInstant(ZoneId.of(zoneId)))
              .sorted()
              .forEach(System.out::println);

    }

}
