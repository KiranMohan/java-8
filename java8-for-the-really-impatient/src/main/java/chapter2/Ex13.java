/**
 * file       : Ex13.java
 * author     : "Kiran Mohan"
 * created on : Mar 13, 2015
 */
package chapter2;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import static org.junit.Assert.*;

import common.Helper;

/**
 * Repeat the preceding exercise, but filter out the short strings and use the
collect method with Collectors.groupingBy and Collectors.counting .
 * @author "Kiran Mohan"
 *
 */
public class Ex13 {
    
    /**
     * @param words
     * @return
     */
    public static Map<Integer, Long> numOfWordsOfLength(String[] words) {
        return Stream.of(words)
                .parallel()
                .filter(w -> w.length() > 6)
                .collect(Collectors.groupingBy(String::length,Collectors.counting()));
    }
    
    @Test
    public void test() {
        String[] words = Helper.getWordsAsArray("src/main/resources/alice.txt");
        Map<Integer, Long> result = numOfWordsOfLength(words);
        for(Integer length : result.keySet()){
            long expectedCount = Stream.of(words).filter(w -> w.length() == length).count();
            assertEquals(expectedCount, result.get(length).longValue());
        }
    }

}
