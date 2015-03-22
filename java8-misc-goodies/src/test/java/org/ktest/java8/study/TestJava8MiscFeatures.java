/**
 * file       : TestJava8MiscFeatures.java
 * author     : "Kiran Mohan"
 * created on : Mar 22, 2015
 */
package org.ktest.java8.study;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;



/**
 * @author "Kiran Mohan"
 *
 */
public class TestJava8MiscFeatures {

    @Test
    public void testStringJoin() {
        String [] path = { "usr", "bin", "java" };
        String joinedString = String.join("/", path);
        assertThat(joinedString, equalTo("usr/bin/java"));
        
        Integer[] ints = {1, 2, 3 };
        joinedString = Stream.of(ints).map(i -> i.toString()).collect(Collectors.joining(", "));
        assertThat(joinedString, equalTo("1, 2, 3"));
    }
    
    @Test
    public void unsignedIntegerSupport() {
        Integer unsignedIntValue = Short.toUnsignedInt((short) -10);
        assertEquals(Integer.valueOf(65526), unsignedIntValue);
        
        unsignedIntValue = Byte.toUnsignedInt(Byte.valueOf("-10"));
        assertEquals(Integer.valueOf(246), unsignedIntValue);
        
        System.out.println("Integer min " + Integer.MIN_VALUE + " in binary: " + Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println("Integer max  " + Integer.MAX_VALUE + " in binary: " + Integer.toBinaryString(Integer.MAX_VALUE));

        Long unsignedLongValue = Integer.toUnsignedLong(Integer.MIN_VALUE); // or Byte.toUnsignedLong(x), 
                                                                            // or Short.toUnsignedLong(x)
        System.out.println("unsigned long " + unsignedLongValue + " in binary " + Long.toBinaryString(unsignedLongValue));
        assertEquals(Long.valueOf(Integer.MAX_VALUE + 1l), unsignedLongValue);
        
    }
    
    @Test
    public void testWrapperClassReductionFunctions() {
        
        assertEquals(3, Integer.sum(1, 2));
        
        List<Predicate<Integer>> conditions  = Arrays.asList(x -> 2 < x,
                                                             x -> x < 10,
                                                             x -> x % 2 != 0);
        // test whether 7 satisfies the given conditions of not.
        // this code is only an example and probably has not practical value; 
        conditions.stream()
                  .reduce(true,  
                          (a, c) -> Boolean.logicalAnd(a, c.test(7)), 
                          Boolean::logicalAnd
                          ); 
  
    }
    
    @Test
    public void test_Collection_removeIf() {
        /*
         * Collection interface has new method removeIf().
         */
        
        Random random = new Random();
        List<Integer> ints = random.ints(100)
                                   .mapToObj(Integer::valueOf)
                                   .collect(Collectors.toList());
        // remove all even numbers
        ints.removeIf(x -> x%2 == 0);
        ints.forEach(x -> assertTrue(x%2 != 0));
    }
    
    @Test
    public void test_List_replaceAll_And_sort() {
        /*
         * List has new methods replaceAll() and sort();
         */
        Random random = new Random();
        List<Integer> ints = random.ints(10, 1, 100) // number b/w 0 and 100
                                   .mapToObj(Integer::valueOf)
                                   .collect(Collectors.toList());
        ints.sort(Integer::compare);
        System.out.println(ints);
        
        ints.replaceAll(x -> x*2);
        System.out.println(ints);
    }
    
    @Test
    public void test_Iterator_forEachRemaining() {
        /*
         * Iterator has forEachRemaining
         */
        Random random = new Random();
        List<Integer> ints = random.ints(10, -100, 100) // number b/w 0 and 100
                                   .mapToObj(Integer::valueOf)
                                   .collect(Collectors.toList());
        
        ints.sort(Comparator.naturalOrder());
        
        // silly example:
        // skip all -ve integers and print the positive values
        Iterator<Integer> itr = ints.iterator();
        while(itr.hasNext() && itr.next() < 0) {
        }
        itr.forEachRemaining(x -> System.out.println(x));
        assertFalse(itr.hasNext()); // assert that the iterator has iterated all the elements
    }
    
    @Test
    public void test_Files_list_And_walk () throws Exception{
        
        System.out.println("Files List: ");
        /*
         * list directory contents 
         */
        try (Stream<Path> paths = Files.list(Paths.get("../../java-8"))) {
            paths.forEach(System.out::println);
        }
        
        System.out.println("Files walk: ");
        /*
         * recursively list contents of directory and its subdirectories. 
         */
        try (Stream<Path> paths = Files.walk(Paths.get("../../java-8"))) {
            paths.filter(p -> !p.toString().matches(".*(target|\\.git).*") && Files.isRegularFile(p))
                 .forEach(System.out::println);
        }
    }
    
}
