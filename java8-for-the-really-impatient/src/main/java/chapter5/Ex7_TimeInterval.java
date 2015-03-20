/**
 * file       : Ex7_TimeInterval.java
 * author     : Kiran Mohan
 * created on : 20-Mar-2015
 */
package chapter5;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * Implement a TimeInterval class that represents an interval of time, suitable
 * for calendar events (such as a meeting on a given date from 10:00 to 11:00).
 * Provide a method to check whether two intervals overlap.
 * 
 * @author Kiran Mohan
 *
 */
public class Ex7_TimeInterval {

    /**
     * @param args
     */
    public static void main(String[] args) {
        TimeInterval ti1 = new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 00));
        TimeInterval ti2 = new TimeInterval(LocalTime.of(11, 0), LocalTime.of(13, 0));
        TimeInterval ti3 = new TimeInterval(LocalTime.of(9, 0), LocalTime.of(11, 0));
        TimeInterval ti4 = new TimeInterval(LocalTime.of(10, 30), LocalTime.of(11, 30));
        TimeInterval ti5 = new TimeInterval(LocalTime.of(12, 0), LocalTime.of(13, 0));
        
        System.out.println("duration : " + ti1.getDuration(ChronoUnit.MINUTES));
        System.out.println(ti1.isOverlapping(ti2));
        System.out.println(ti1.isOverlapping(ti3));
        System.out.println(ti1.isOverlapping(ti4));
        System.out.println(ti1.isOverlapping(ti5));
    }

}

class TimeInterval {
    private final LocalTime start;
    private final LocalTime end;

    public TimeInterval(LocalTime start, LocalTime end) {
        super();
        this.start = start;
        this.end = end;
    }

    public long getDuration(TemporalUnit unit) {
        return start.until(end, unit);
    }

    public boolean isOverlapping(final TimeInterval other) {
        if (this.start.isBefore(other.start) && this.end.isAfter(other.start)) {
            return true;
        }
        if (this.start.isBefore(other.end) && this.end.isAfter(other.end)) {
            return true;
        }
        return false;
    }
}