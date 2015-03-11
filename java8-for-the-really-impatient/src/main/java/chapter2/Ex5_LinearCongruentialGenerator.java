/**
 * file       : Ex5_LinearCongruentialGenerator.java
 * author     : "Kiran Mohan"
 * created on : Mar 11, 2015
 */
package chapter2;

import java.util.stream.Stream;

/**
 * Using Stream.iterate , make an infinite stream of random numbers—not by
calling Math.random but by directly implementing a linear congruential generator.
In such a generator, you start with X0 = seed and then produce Xn + 1 =
(a * Xn + c) % m, for appropriate values of a, c, and m. You should implement a
method with parameters a , c , m , and seed that yields a Stream<Long> . Try out a =
25214903917, c = 11, and m = 2^48 .
 * @author "Kiran Mohan"
 *
 */
public class Ex5_LinearCongruentialGenerator {

    /**
     * @param args
     */
    public static void main(String[] args) {
        final long a = 25214903917l;
        final long c = 11l;
        final long m = 1l << 48;

//        LinearCongruentialGenerator generator = new LinearCongruentialGenerator(a, c, m, 0);
//        Stream.generate(generator::next).limit(10l).forEach(System.out::println);
        
        // OR simply
        Stream.iterate(0l, x -> (a * x + c) % m).limit(10l).forEach(System.out::println);

    }

}

class LinearCongruentialGenerator {
    private final long a;
    private final long c;
    private final long m;
    private long x;
    
    /**
     * linear congruential generator. In such a generator, you start with X0 =
     * seed and then produce Xn + 1 = (a * Xn + c) % m, for appropriate values
     * of a, c, and m.
     * 
     * @param a
     * @param c
     * @param m
     * @param seed
     */
    public LinearCongruentialGenerator(long a, long c, long m, long seed) {
        super();
        this.a = a;
        this.c = c;
        this.m = m;
        this.x = seed;
    }
    
    public long next() {
        x = (a * x + c) % m;
        return x;
    }
    
}