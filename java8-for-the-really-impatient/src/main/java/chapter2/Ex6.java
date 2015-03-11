/**
 * file       : Ex6.java
 * author     : "Kiran Mohan"
 * created on : Mar 11, 2015
 */
package chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The characterStream method in Section 2.3, “The filter , map , and flatMap Methods,”
on page 25, was a bit clumsy, first filling an array list and then turning it
into a stream. Write a stream-based one-liner instead. One approach is to
make a stream of integers from 0 to s.length() - 1 and map that with the
s::charAt method reference.
 * @author "Kiran Mohan"
 *
 */
public class Ex6 {
    
    public static Stream<Character> characterStream(String s) {
//        List<Character> result = new ArrayList<>();
//        for (char c : s.toCharArray())
//            result.add(c);
//        return result.stream();
        
        // OR
        
        //return IntStream.range(0, s.length()).mapToObj(s::charAt);

        // OR
        
        return s.chars().mapToObj(c -> new Character((char)c));
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        characterStream("Kiran").forEach(System.out::println);
    }

}
