/**
 * file       : Ex20.java
 * author     : "Kiran Mohan"
 * created on : Mar 16, 2015
 */
package chapter3;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Supply a static method <T, U> List<U> map(List<T>, Function<T, U>).
 * @author "Kiran Mohan"t
 *
 */
public class Ex20 {
    
    public static <T, U> List<U> map(List<T> list, Function<T, U> function) {
        return list.stream().map(function::apply).collect(Collectors.toList());
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("one", "two", "three");
        List<Integer> lengths = map(strings, String::length);
        System.out.println(lengths);
    }
}