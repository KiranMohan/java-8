/**
 * file       : Ex1.java
 * author     : "Kiran Mohan"
 * created on : Mar 22, 2015
 */
package chapter8;

import java.util.Comparator;

/**
 * Write a program that adds, subtracts, divides, and compares numbers between 0
 * and 2^32 – 1, using int values and unsigned operations. Show why
 * divideUnsigned and remainderUnsigned are necessary.
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex1 {

    /**
     * @param args
     */
    public static void main(String[] args) {
       System.out.println("add unsigned -3, 4 : " + Unsigned.add(-3, 4));
       
       System.out.println("subtract unsigned -3, 4 " + Unsigned.subtract(-3, 4));
       
       System.out.println("-12/5 quotient : " + Integer.divideUnsigned(-12, 5));
       System.out.println("-12/5 remainder : " + Integer.remainderUnsigned(-12, 5));
       
       System.out.println("comparing unsigned -3, 4 : " + Integer.compareUnsigned(-3, 4));
    }

}

class Unsigned{

    public static long add(int x, int y) {
        return Integer.toUnsignedLong(x) + Integer.toUnsignedLong(y);
    }
    
    public static long subtract(int x, int y) {
        return Integer.toUnsignedLong(x) - Integer.toUnsignedLong(y);
    }
}