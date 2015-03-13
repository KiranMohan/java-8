/**
 * file       : Ex12.java
 * author     : "Kiran Mohan"
 * created on : Mar 13, 2015
 */
package chapter2;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import common.Helper;

/**
 * Count all short words in a parallel Stream<String> , as described in Section
 * 2.13, “Parallel Streams,” on page 40, by updating an array of AtomicInteger .
 * Use the atomic getAndIncrement method to safely increment each counter.
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex12 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] words = Helper.getWordsAsArray("src/main/resources/alice.txt");
        AtomicInteger[] shortWords =Stream.generate(()->new AtomicInteger(0))
                                          .limit(12)
                                          .toArray(AtomicInteger[]::new);
        Stream.of(words).parallel().forEach(s -> {
            if (s.length() < 12)
                shortWords[s.length()].getAndIncrement();
        });
        System.out.println(Arrays.toString(shortWords));
    }

}
