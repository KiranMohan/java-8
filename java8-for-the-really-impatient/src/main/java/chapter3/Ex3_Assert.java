/**
 * file       : Ex3_Assert.java
 * author     : "Kiran Mohan"
 * created on : Mar 13, 2015
 */
package chapter3;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * Java 1.4 added assertions to the language, with an assert keyword. Why were
assertions not supplied as a library feature? Could they be implemented as
a library feature in Java 8?
 * @author "Kiran Mohan"
 *
 */
public class Ex3_Assert {
    public static boolean enableAssert = false;
    public static void assertThat(BooleanSupplier assertion) {
        if (enableAssert && !assertion.getAsBoolean()) {
            throw new AssertionError();
        }
    }
    
    public static <T> void assertThat(BooleanSupplier assertion, Supplier<T> expr) {
        if (enableAssert && !assertion.getAsBoolean()) {
            System.out.println("error 2");
            throw new AssertionError(expr.get());
        }
    }
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        enableAssert = true;
        assertThat(()-> 1 < 2);
        //assertThat(()-> 1 > 2);
        //assertThat(()-> 1 > 2, () -> "nonesense");
        
    }

}
