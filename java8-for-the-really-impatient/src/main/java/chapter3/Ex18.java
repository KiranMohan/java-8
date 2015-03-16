/**
 * file       : Ex18.java
 * author     : "Kiran Mohan"
 * created on : Mar 16, 2015
 */
package chapter3;

import java.util.function.Function;

interface ThrowFunction<T,R> {
    R apply(T t) throws Exception;
}

/**
 * Implement a version of the unchecked method in Section 3.8, “Dealing with
 * Exceptions,” on page 58, that generates a Function<T, U> from a lambda that
 * throws checked exceptions. Note that you will need to find or provide a
 * functional interface whose abstract method throws arbitrary exceptions.
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex18 {

    public static <T, R> Function<T,R> unchecked(ThrowFunction<T,R> f) {
        return (arg) -> {
            try {
                return f.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } catch (Throwable t) {
                throw t;
            }
        };
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ThrowFunction<Integer, Integer> checkZeroFunction = (arg) -> {
            System.out.println("arg : " + arg);
            if (arg == 0) {
                System.out.println("NOK");
                throw new IllegalArgumentException("arg cannot be 0");
            } else
                System.out.println("OK");
            return arg;
        };
        
        Function<Integer, Integer> f1 = unchecked(checkZeroFunction);
        f1.apply(1);
        f1.apply(0);
    }

}

