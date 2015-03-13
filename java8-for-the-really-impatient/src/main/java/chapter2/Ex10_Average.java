/**
 * file       : Ex10_Average.java
 * author     : "Kiran Mohan"
 * created on : Mar 12, 2015
 */
package chapter2;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Write a call to reduce that can be used to compute the average of a Stream<Double> .
   Why can’t you simply compute the sum and divide by count() ?
 * @author "Kiran Mohan"
 *
 */
public class Ex10_Average {
    
    public static Double average_sumByCount(Stream<Double> doubles) {
        // Why can’t you simply compute the sum and divide by count() ?
        AtomicInteger i = new AtomicInteger(1);
        Optional<Double> result = doubles.parallel()
                                         .reduce((d1, d2) -> {
                                                i.getAndIncrement();
                                                return d1 + d2;
                                            });
        return result.orElse(Double.NaN)/i.get();
    }
    
    public static Double average_usingReduce(Stream<Double> doubles) {
        Average result = doubles.parallel()
                .reduce(new Average(0, 0.0), 
                        Average::accumulate, 
                        Average::combine);
        return result.getValue();
    }
    
    @Test
    public void test() {
        Random random = new Random();
        List<Double> doubles = random.doubles(10000)
                                     .boxed()
                                     .collect(Collectors.toCollection(ArrayList::new));
        
        DoubleSummaryStatistics expectedStats = doubles.stream()
                                                       .mapToDouble(Double::doubleValue)
                                                       .summaryStatistics();
        System.out.println("correct average    : " + expectedStats.getAverage());
        
        
        // average_sumByCount
        Double ave1 = average_sumByCount(doubles.stream());
        System.out.println("average_sumByCount : " + ave1);
        assertEquals(expectedStats.getAverage(), ave1, 0.0001);
        
        // average_usingReduce
        Double ave2 = average_usingReduce(doubles.stream());
        System.out.println("average_usingReduce : " + ave2);
        assertEquals(expectedStats.getAverage(), ave2, 0.0001);
    }

}

class Average {

    /**
     * @param n
     * @param value
     */
    public Average(final int n, final double value) {
        super();
        this.n = n;
        this.value = value;
    }

    public static Average accumulate(Average a, Double d) {
        int n = a.n + 1;
        double value =  (a.value * a.n + d) / n;
        return new Average(n, value);
    }
    
    public static Average combine(Average a1, Average a2) {
        int n = a1.n + a2.n;
        double value = (a1.value * a1.n + a2.value * a2.n) / n;
        return new Average(n, value);
    }
    

    /**
     * @return the n
     */
    public int getN() {
        return n;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }


    private final int n;
    private final double value;
    
    
    
}
