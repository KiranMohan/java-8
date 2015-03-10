/**
 * file       : Ex6_UncheckExceptions.java
 * author     : Kiran Mohan
 * created on : 04-Mar-2015
 */
package chapter1;

import java.util.concurrent.Callable;

interface RunnableEx {
    public void run() throws Exception;
}

/**
 * Write a method uncheck that catches all checked exceptions and turns them 
 * into unchecked exceptions. 
 * @author Kiran Mohan
 *
 */
public class Ex6_UncheckExceptions {

    public static Runnable uncheck(RunnableEx runEx) {
        return () -> {
            try {
                runEx.run();
            } catch(Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }
    
    public static Runnable uncheck(Callable<Void> runEx) {
        return () -> {
            try {
                runEx.call();
            } catch(Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {        
        uncheck(()-> {
        	System.out.println("runnable");
        	Thread.sleep(100);
        }).run();
        
        uncheck(()->{
        	System.out.println("callable");
        	return null;
        }).run();
    }

}
