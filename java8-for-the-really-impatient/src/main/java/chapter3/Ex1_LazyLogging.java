/**
 * file       : Ex1_LazyLogging.java
 * author     : "Kiran Mohan"
 * created on : Mar 13, 2015
 */
package chapter3;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Enhance the lazy logging technique by providing conditional logging. A
typical call would be logIf(Level.FINEST, () -> i == 10, () -> "a[10] = " + a[10]) .
Don’t evaluate the condition if the logger won’t log the message.
 * @author "Kiran Mohan"
 *
 */
public class Ex1_LazyLogging {
    
    private static Logger log = Logger.getGlobal();
    
    public static <T> void logIf(Level level, BooleanSupplier condition , Supplier<String> msg) {
        
        if (log.isLoggable(level) && condition.getAsBoolean()) {
            log.log(level, msg);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        log.setLevel(Level.ALL);
        int i = 10;
        logIf(Level.INFO, () -> i >   9, () -> "test " + 1);
        logIf(Level.INFO, () -> i >  11, () -> "test " + 2);
        logIf(Level.INFO, () -> i == 10, () -> "test " + 3);
        logIf(Level.INFO, () -> i <   9, () -> "test " + 4);
        logIf(Level.INFO, () -> i <  11, () -> "test " + 5);
    }

}
