/**
 * file       : Ex2.java
 * author     : "Kiran Mohan"
 * created on : Mar 22, 2015
 */
package chapter8;

/**
 * For which integer n does Math.negateExact(n) throw an exception? (Hint: There is only one.)
 * @author "Kiran Mohan"
 *
 */
public class Ex2 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Math.negateExact(0));
        System.out.println(Math.negateExact(Integer.MAX_VALUE));
        System.out.println(Math.negateExact(Integer.MIN_VALUE));
    }

}
