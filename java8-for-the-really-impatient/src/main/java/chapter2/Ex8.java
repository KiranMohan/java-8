/**
 * file       : Ex8.java
 * author     : "Kiran Mohan"
 * created on : Mar 11, 2015
 */
package chapter2;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Write a method public static <T> Stream<T> zip(Stream<T> first, Stream<T> second)
that alternates elements from the streams first and second , stopping when
one of them runs out of elements.
 * @author "Kiran Mohan"
 *
 */
public class Ex8 {
    
    public static <T> Stream<T> zip1(Stream<T> first, Stream<T> second) {
        List<T> firstList = first.collect(Collectors.toList());
        List<T> secondList = second.collect(Collectors.toList());
        AtomicInteger i = new AtomicInteger(0);
        return Stream.generate(() -> {
                            int j = i.getAndIncrement();
                            return Stream.of(firstList.get(j), secondList.get(j));
                     })
                     .limit(Math.min(firstList.size(), secondList.size()))
                     .flatMap(x -> x);
    }
    
    public static <T> Stream<T> zip2(Stream<T> first, Stream<T> second) {
        Iterator<T> secondItr = second.iterator();
        Stream.Builder<T> builder = Stream.builder();
        first.forEach(e -> {
            if (secondItr.hasNext()) {
                builder.accept(e);
                builder.accept(secondItr.next());
            } else {
                first.close();
            }
        });

        return builder.build();
    }
    
    public static <T> Stream<T> zip3(Stream<T> first, Stream<T> second) {
        Iterator<T> firstItr = first.iterator();
        Iterator<T> secondItr = second.iterator();
        Stream.Builder<T> builder = Stream.builder();
        while (firstItr.hasNext() && secondItr.hasNext()) {
            builder.accept(firstItr.next());
            builder.accept(secondItr.next());
        }

        return builder.build();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> first  = Stream.iterate(1, i -> i += 2).limit(10).collect(Collectors.toList());
        List<Integer> second = Stream.iterate(2, i -> i += 2).limit(20).collect(Collectors.toList());
        System.out.println("result zip1() : " + zip1(first.stream(), second.stream())
                                            .map(Object::toString)
                                            .collect(Collectors.joining(", ")));
        System.out.println("result zip2() : " + zip2(first.stream(), second.stream())
                .map(Object::toString)
                .collect(Collectors.joining(", ")));
        System.out.println("result zip3() : " + zip3(first.stream(), second.stream())
                .map(Object::toString)
                .collect(Collectors.joining(", ")));
    }

}
