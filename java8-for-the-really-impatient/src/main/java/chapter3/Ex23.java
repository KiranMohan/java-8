/**
 * file       : Ex23.java
 * author     : "Kiran Mohan"
 * created on : Mar 16, 2015
 */
package chapter3;

import java.util.function.Function;


/**
 * Define a map operation for a class Pair<T> that represents a pair of objects of
type T .
 * @author "Kiran Mohan"
 *
 */
public class Ex23 {
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        Pair<Integer> pi = new Pair<>(1,2);
        
        Integer result = pi.map(Math::multiplyExact);
        System.out.println(result);
        
    }
    

}