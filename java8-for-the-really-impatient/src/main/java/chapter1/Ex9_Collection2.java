/**
 * file       : Ex9_Collection2.java
 * author     : Kiran Mohan
 * created on : 10-Mar-2015
 */
package chapter1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Form a subclass Collection2 from Collection and add a default method {@code 
   void forEachIf(Consumer<T> action, Predicate<T> filter)} that applies  
   action to each element for which filter returns true. How could you use it?
 * @author Kiran Mohan
 *
 */
public class Ex9_Collection2 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> ints = Stream.iterate(1, x -> x+1)
                                   .limit(10)
                                   .collect(Collectors.toList());
        ArrayList2<Integer> ints2 = new ArrayList2<>(ints);
        ints2.forEachIf(System.out::println, x -> x > 5);       
    }

}

interface Collection2<T> extends Collection<T> {
    default void forEachIf(Consumer<T> action, Predicate<T> filter) {
        for(T t: this) {
            if (filter.test(t)) {
                action.accept(t);
            }
        }
    }
}

class ArrayList2<T> extends ArrayList<T> implements Collection2<T> {
    private static final long serialVersionUID = 1L;

    public ArrayList2(Collection<T> collection) {
        super(collection);
    }
}