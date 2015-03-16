/**
 * file       : Ex21.java
 * author     : "Kiran Mohan"
 * created on : Mar 16, 2015
 */
package chapter3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * Supply a static method <T, U> Future<U> map(Future<T>, Function<T, U>) .
 * Return an object of an anonymous class that implements all methods of the
 * Future interface. In the get methods, invoke the function.
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex21 {

    public static <T, U> Future<U> map(Future<T> future, Function<T, U> function) {
        return new Future<U>() {

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean isCancelled() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean isDone() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public U get() throws InterruptedException, ExecutionException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public U get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return function.apply(future.get());
            }
        };
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
