/**
 * file       : Ex17.java
 * author     : "Kiran Mohan"
 * created on : Mar 16, 2015
 */
package chapter3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.function.BiConsumer;

/**
 * Implement a doInParallelAsync(Runnable first, Runnable second,
 * Consumer<Throwable>) method that executes first and second in parallel,
 * calling the handler if either method throws an exception.
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex17 {

    public static void doInParallelAsync(Runnable first, Runnable second, BiConsumer<Thread, Throwable> handler) {
        ThreadFactory threadFactory = r -> {
            System.out.println("Creating new thread");
            Thread t = new Thread(r, r.getClass().getSimpleName());
            t.setUncaughtExceptionHandler((thread, exception) -> handler.accept(thread, exception));
            return t;
        };
        ExecutorService executors = Executors.newFixedThreadPool(2, threadFactory);
        executors.execute(first);
        executors.execute(second);
        executors.shutdown();
    }

    /**, 
     * @param args
     */
    public static void main(String[] args) {
        BiConsumer<Thread, Throwable> handler= (thread, exception) -> {
            System.out.println("In exception handler");
            System.out.println(thread.getName() + " caught exception: " + exception.getMessage());
            exception.printStackTrace();
        };
        
        doInParallelAsync(
        () -> { // first 
            System.out.println("first runnable in run.");
            throw new RuntimeException("first runnable exception");
        }, 
        () -> { // second
            System.out.println("second runnable in run.");
            throw new RuntimeException("second runnable exception");
        }, 
        handler);

    }

}
