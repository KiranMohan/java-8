/**
 * file       : Ex2.java
 * author     : Kiran Mohan
 * created on : 11-Mar-2015
 */
package chapter2;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import common.Helper;

/**
 * Verify that asking for the first five long words does not call the filter method
once the fifth long word has been found. Simply log each method call.
 * @author Kiran Mohan
 *
 */
public class Ex2 {
    
    public static void main(String [] args) {
        String[] words = Helper.getWordsAsArray("src/main/resources/alice.txt");
        AtomicInteger i = new AtomicInteger(0);
        String [] longWords = Stream.of(words)
                               .filter(w -> {
                                    if (w.length() > 12) {
                                        System.out.println(i.incrementAndGet() + ". found");
                                        return true;
                                    } else {
                                        return false;
                                    }
                                })
                                .limit(5)
                                .toArray(String[]::new);
        System.out.println("number of times filter method called: " + i);
        System.out.println("number of long words: " + Arrays.toString(longWords));
    }

}
