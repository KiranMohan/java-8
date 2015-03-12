/**
 * file       : Ex9_StreamReduce.java
 * author     : Kiran Mohan
 * created on : 12-Mar-2015
 */
package chapter2;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.Helper;

/**
 * Join all elements in a Stream<ArrayList<T>> to one ArrayList<T>. Show how to do this with the three forms of reduce.
 * @author Kiran Mohan
 *
 */
public class Ex9_StreamReduce {
    
    /**
     * Stream<ArrayList<T>> to one ArrayList<T> using single flatMap()
     * @param stream
     * @return
     */
    public static <T> List<T> reduceToSingleList1(Stream<List<T>> stream) {
        List<T> list = stream.flatMap(x -> x.stream())
                             .collect(Collectors.toList());
                             //.collect(Collectors.toCollection(ArrayList<T>::new));
        return list;
    }
    
    @Test
    public void testReduceToSingleList1() {
        List<Integer> singleList = reduceToSingleList1(streamOfLists);
        System.out.println(singleList);
        assertThat(singleList, equalTo(expectedFinalList));
        // System.out.println(first);
        // System.out.println(second);
        // System.out.println(third);
    }
    
    /**
     * Stream<ArrayList<T>> to one ArrayList<T> using the form values.reduce((x, y) -> x + y).<p>
       In this case, the reduce method computes v0 + v1 + v2 + ..., where the vi are the stream elements.
     * @param stream
     * @return
     */
    public static <T> List<T> reduceToSingleList2(Stream<List<T>> stream) {
        Optional<List<T>> optionalList = stream.reduce((list1, list2) -> {
            List<T> newList = new ArrayList<T>(list1);
            newList.addAll(list2);
            return newList;
        });
        return optionalList.orElse(Collections.emptyList());
    }
    
    @Test
    public void testReduceToSingleList2() {
        List<Integer> singleList = reduceToSingleList2(streamOfLists);
        System.out.println(singleList);
        assertThat(singleList, equalTo(expectedFinalList));
        // System.out.println(first);
        // System.out.println(second);
        // System.out.println(third);
    }
    
    /**
     * Stream<ArrayList<T>> to one ArrayList<T> using the form values.reduce(0, (x, y) -> x + y);<p>
       Computes 0 + v0 + v1 + v2 + ...<br>
       The identity value is returned if the stream is empty, and you no longer need to deal with the Optional class.
     * @param stream
     * @return
     */
    public static <T> List<T> reduceToSingleList3(Stream<List<T>> stream) {
       List<T> list = stream.reduce(new ArrayList<T>(), (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        });
        return list;
    }

    @Test
    public void testReduceToSingleList3() {
        List<Integer> singleList = reduceToSingleList3(streamOfLists);
        System.out.println(singleList);
        assertThat(singleList, equalTo(expectedFinalList));
        // System.out.println(first);
        // System.out.println(second);
        // System.out.println(third);
    }
    
    /**
     * Suppose you have a stream of objects and want to form the sum of some property, such as all lengths in a stream 
     * of strings. You can’t use the simple form of reduce. It requires a function (T, T) -> T, with the same types for
     * the arguments and the result. But in this situation, you have two types. The stream elements have type String, 
     * and the accumulated result is an integer. There is a form of reduce that can deal with this situation.<p>
    
        First, you supply an “accumulator” function {@code (total, word) -> total + word.length()}. That function is called 
        repeatedly, forming the cumulative total. But when the computation is parallelized, there will be multiple 
        computations of this kind, and you need to combine their results. You supply a second function for that purpose. 
        The complete call is <b>
        <pre>
        int result = words.reduce(0,
        (total, word) -> total + word.length(),
        (total1, total2) -> total1 + total2);
        </pre>
     * @param stream
     * @return
     */
    public static <T> List<T> reduceToSingleList4(Stream<List<T>> stream) {
        // this is a bad example here 
        List<T> list = stream.reduce(new ArrayList<T>(), (list1, list2) -> {
           list1.addAll(list2);
           return list1;
        }, (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        });
        return list;
    }
    
    @Test
    public void testReduceToSingleList4() {
        List<Integer> singleList = reduceToSingleList4(streamOfLists);
        System.out.println(singleList);
        assertThat(singleList, equalTo(expectedFinalList));
        // System.out.println(first);
        // System.out.println(second);
        // System.out.println(third);
    }
    
    
    
    private static List<Integer> first;
    private static List<Integer> second;  
    private static List<Integer> third;
    private Stream<List<Integer>> streamOfLists;
    
    private static List<Integer> expectedFinalList;

    @BeforeClass
    public static void staticSetup() {
        first  = Helper.getIntegerSequence( 1, 1, 10);
        second = Helper.getIntegerSequence(11, 1, 10);
        third  = Helper.getIntegerSequence(21, 1, 10);
        expectedFinalList = Helper.getIntegerSequence(1, 1, 30);
    }
    
    @Before
    public void setup() {
        streamOfLists = Stream.of(first, second, third);
    }

}
