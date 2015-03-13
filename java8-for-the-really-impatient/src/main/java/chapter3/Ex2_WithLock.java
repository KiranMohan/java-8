/**
 * file       : Ex2_WithLock.java
 * author     : "Kiran Mohan"
 * created on : Mar 13, 2015
 */
package chapter3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * When you use a ReentrantLock , you are required to lock and unlock with the idiom
    
    <br><pre>
    myLock.lock();
    try {
        ...some action
    } finally {
        myLock.unlock();
    }
    </pre><br>
    
    Provide a method withLock so that one can call<br>
    <pre>        withLock(myLock, () -> { some action }) 
    </pre>
    
 * @author "Kiran Mohan"
 *
 */
public class Ex2_WithLock {
    
    public static void withLock(Lock lock, Runnable runnable) {
        try {
            lock.lock();
            runnable.run();
        } finally {
            lock.unlock();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
       
        Lock lock = new ReentrantLock(){
            private static final long serialVersionUID = 1L;
            @Override
            public void lock() {
                System.out.println("locking");
                super.lock();
            }
            @Override
            public void unlock() {
                System.out.println("unlocking");
                super.unlock();
            }
        };
        withLock(lock, ()-> System.out.println("Hello World"));

    }

}
