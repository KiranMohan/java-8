/**
 * file       : Ex7_AndThen.java
 * author     : Kiran Mohan
 * created on : 04-Mar-2015
 */
package chapter1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Write a static method {@code andThen} that takes as parameters two Runnable instances
 *  and returns a Runnable that runs the first, then the second. In the main 
 *  method, pass two lambda expressions into a call to andThen, and run the 
 *  returned instance
 * @author Kiran Mohan
 *
 */
public class Ex7_AndThen {

    public static void andThen(Runnable f, Runnable s) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(f);
        executor.submit(s);
        executor.shutdown();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        andThen(() -> {
            try {
                TimeUnit.SECONDS.sleep(2l);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("first");
        }, () -> {
            System.out.println("second");
        });
    }

}
