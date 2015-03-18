/**
 * file       : TestJavaTimeInstant.java
 * author     : "Kiran Mohan"
 * created on : Mar 18, 2015
 */
package org.ktest.java8.study.datetime;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

/**
 * @author "Kiran Mohan"
 *
 */
public class TestJavaTimeInstant {

    @Test
    public void test_Duration_Instant() {
        Instant startInstant = Instant.now();
        
        Random random = new Random();       
        int[] ints = random.ints().limit(10000000).toArray();
        Arrays.sort(ints);
        
        Instant stopInstant = Instant.now();
        
        Duration processingTime = Duration.between(startInstant, stopInstant);
        System.out.println("Processing Time : " + processingTime.toNanos() + " ns");
        System.out.println("Processing Time : " + processingTime.toMillis() + " ms");
        
        System.out.println("Seconds         : " + processingTime.getSeconds());
        System.out.println("nanoseconds     : " + processingTime.getNano());
        
        assertFalse(processingTime.isZero());
        assertFalse(processingTime.isNegative());
        
        // lets reverse the calculation
        processingTime = Duration.between(stopInstant, startInstant);
        assertFalse(processingTime.isZero());
        assertTrue(processingTime.isNegative());

    }
    
    @Test
    public void test_Duration_Instant_Arithmetics(){
        
        Instant i1 = Instant.ofEpochSecond(1000);
        Instant i2 = i1.minus(Duration.ofSeconds(100));
        assertThat(i1.getEpochSecond(), is( equalTo( i2.getEpochSecond() + 100 )));
        
        System.out.println("i1 : " + i1.getEpochSecond());
        System.out.println("i2 : " + i2.getEpochSecond());
        
        Instant i3 = i1.minusSeconds(100l);
        assertEquals(i2, i3);
        
        assertTrue(i1.isAfter(i2));
        
        Instant i4 = i1.plusSeconds(100);
        assertThat(i4.getEpochSecond(), equalTo(i1.getEpochSecond() + 100));
        
    }

}
