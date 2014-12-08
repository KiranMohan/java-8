/**
 * file       : StreamReductionsTest.java
 * author     : Kiran Mohan
 * created on : 29-Nov-2014
 */
package org.ktest.study.java8.stream;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

/**
 * @author Kiran Mohan
 *
 */
public class StreamReductionsTest {
    
    @Test
    public void reduce() {
        
        List<Integer> ints = Stream.iterate(1, x -> ++x)
                                   .limit(10)
                                   .collect(Collectors.toList());
        
        // -------------------------------------------------------------------------
        // first form
        Optional<Integer> result = ints.stream()
                                       //.reduce((x,y) -> x + y);
                                       .reduce(Integer::sum); // same as above line
        result.ifPresent(x -> assertThat(x, is(55)));
        // -------------------------------------------------------------------------
        
        // second form
        // notice return type is not Optional<Integer>. Why?
        Integer result2 = ints.stream()
                              .reduce(7, Integer::sum); // 7 is the seed value
        assertThat(result2, is(62));
        // -------------------------------------------------------------------------
        
        // third form, especially needed with parallel streams
        Integer result3 = wordList.parallelStream()
                                  .reduce( 0,           // 0 is the seed value
                                          (total, word) -> total + word.length(),
                                          //(total1, total2) -> total1 + total2   // same as Integer::sum
                                          Integer::sum
                                         );
        System.out.println("number of characters : " + result3);
        // -------------------------------------------------------------------------
    }
    
    

    @Test
    public void max() {
        // find the lengthiest word in the list
        Optional<String> result = wordList.stream()
                                          .max(Comparator.comparing(String::length));
        result.ifPresent(x -> {
            System.out.println(x);
            assertThat(x, is("unenforceability"));
        });
    }
        
    
    @Test
    public void findOperations() {
        
        // findFirst()
        Optional<String> result = wordList.stream()
                                          .filter(w -> w.startsWith("Q"))
                                          .findFirst();
        result.ifPresent(x -> assertThat(x,startsWith("Q")));
        result.ifPresent(System.out::println);
        
        // findAny(). useful in parallelized streams
        result = wordList.parallelStream()
                         .filter(w -> w.startsWith("Q"))
                         .findAny();
        result.ifPresent(x -> assertThat(x, startsWith("Q")));
        result.ifPresent(System.out::println);
        
        // checkout anyMatch(), allMatch(), noneMatch()
    }
    
    @Test
    public void count(){
        List<Integer> ints = Stream.iterate(1, x -> ++x)
                .limit(10)
                .collect(Collectors.toList());
        
        assertThat(ints.stream().count(), is(10l));
    }

    
    @BeforeClass
    public static void prepareWordList() {
        // 1. filter
        try (Stream<String> lines = Files.lines(Paths.get("../alice.txt"))) {
            
            wordList = lines.flatMap(t -> Stream.of(t.split("\\P{L}")))
                            .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static List<String> wordList;
}
