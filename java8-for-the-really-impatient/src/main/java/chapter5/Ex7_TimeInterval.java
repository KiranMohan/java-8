/**
 * file       : Ex7_TimeInterval.java
 * author     : Kiran Mohan
 * created on : 20-Mar-2015
 */
package chapter5;

import java.time.Duration;
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
        // TODO Auto-generated method stub

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

    public long getInterval(TemporalUnit unit) {
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