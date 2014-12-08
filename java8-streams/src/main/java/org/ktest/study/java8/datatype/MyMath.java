/**
 * file       : MyMath.java
 * author     : Kiran Mohan
 * created on : 29-Nov-2014
 */

package org.ktest.study.java8.datatype;

import java.util.Optional;

/**
 * @author Kiran Mohan
 *
 */
public class MyMath {
    
    public static Double unsafeInverse(Double d) {
        Double result = null;
        if (d != null) {
            result = 1.0/d;
        }
        return result;
    }
    
    public static Double unsafeSqrt(Double d) {
        return Math.sqrt(d);
    }
    
    public static Optional<Double> inverse(Double d) {
        return d == 0 ? Optional.empty() : Optional.of(1.0/d);
    }
    
    public static Optional<Double> sqrt(Double d) {
        return Optional.of(Math.sqrt(d));
    }

}
