/**
 * file       : Ex24.java
 * author     : "Kiran Mohan"
 * created on : Mar 16, 2015
 */
package chapter3;


/**
 * @author "Kiran Mohan"
 *
 */
public class Ex24 {
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        Pair<Integer> pi = new Pair<>(1,2);
        // convert from Integer to Double
        Pair<Double> pd = pi.flatMap(Integer::doubleValue);
        System.out.println(pd);
    }

}

