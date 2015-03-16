/**
 * file       : Ex16.java
 * author     : "Kiran Mohan"
 * created on : Mar 16, 2015
 */
package chapter3;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Implement the doInOrderAsync of Section 3.8, “Dealing with Exceptions,” on
 * page 58, where the second parameter is a BiConsumer<T, Throwable> . Provide a
 * plausible use case. Do you still need the third parameter?
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex16 {

    public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second) {
        Thread t = new Thread() {
            public void run() {
                T result = null;
                Throwable error = null;
                try {
                    result = first.get();
                } catch (Throwable t) {
                    error = t;
                }
                second.accept(result, error);
            }
        };
        t.start();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
       BiConsumer<Integer,Throwable> consumer= (x, error) -> {
           if (x != null ) {
               System.out.println( x);
           } else if (error != null) {
               System.err.println(error.getMessage());
           }
       };
       doInOrderAsync(()-> 10, consumer);
       
       doInOrderAsync(()-> { throw new RuntimeException("Test Exception");}, consumer);
    }
}
