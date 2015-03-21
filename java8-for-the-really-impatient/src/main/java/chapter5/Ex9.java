/**
 * file       : Ex9.java
 * author     : "Kiran Mohan"
 * created on : Mar 21, 2015
 */
package chapter5;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Comparator;

/**
 * Again using stream operations, find all time zones whose offsets aren’t full hours.
 * @author "Kiran Mohan"
 *
 */
public class Ex9 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Instant instantZero = Instant.ofEpochMilli(0);
        ZoneId.getAvailableZoneIds()
              .stream()
              .map(ZoneId::of)
              .filter(id -> instantZero.atZone(id).getOffset().getTotalSeconds() % 3600 != 0)
              .sorted(Comparator.comparing(ZoneId::getId))
              .forEach(System.out::println);
    }

}
