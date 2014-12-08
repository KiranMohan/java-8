/**
 * file       : PrimitiveTypesStream.java
 * author     : Kiran Mohan
 * created on : 29-Nov-2014
 */
package org.ktest.study.java8.stream;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import org.junit.Test;

/**
 * @author Kiran Mohan
 *
 */
public class PrimitiveTypesStreamTest {

    @Test
    public void creation() { 
        // similar examples for DoubleStream, LongStream
        
        // Stream from arrays
        IntStream ints = IntStream.of(0,1,2,3,4,5,6,7,8,9);
        int[] intArray = ints.toArray();
        System.out.println("int stream using of() " + Arrays.toString(intArray));
        
        // stream from subset of arrays
        intArray = Arrays.stream(intArray, 0, 5).toArray();
        System.out.println("int stream using Arrays.stream() " + Arrays.toString(intArray));
        
        // create stream automatically in given range
        intArray = IntStream.range(0, 10).toArray();    // 0 inclusive, 10 exclusive
                                                        // check out rangeClosed()
        System.out.println("int stream using IntStream.range() " + Arrays.toString(intArray));
        
        IntStream charIntStream = "HelloWorld".codePoints();
        List<Character> charList = charIntStream.mapToObj(c -> (char)c).collect(Collectors.toList());
        System.out.println(charList);
    }
    
    @Test
    public void reduction() {
        // similar examples for IntStream, LongStream
        
        double[] doubles = new Random().doubles(10).toArray();
        System.out.println(Arrays.toString(doubles));
        
        System.out.println("sum : " + DoubleStream.of(doubles).sum());
        System.out.println("ave : " + DoubleStream.of(doubles).average());
        
        DoubleSummaryStatistics stats = DoubleStream.of(doubles).summaryStatistics();
        System.out.println("sum : " + stats.getSum());
        System.out.println("ave : " + stats.getAverage()); 
        System.out.println("max : " + stats.getMax());
        
    }

}
