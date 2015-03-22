/**
 * file       : Ex3.java
 * author     : "Kiran Mohan"
 * created on : Mar 22, 2015
 */
package chapter8;

/**
 * Euclid’s algorithm (which is over two thousand years old) computes the
 * greatest common divisor of two numbers as gcd(a, b) = a if b is zero, and
 * gcd(b, rem(a, b)) otherwise, where rem is the remainder. Clearly, the gcd
 * should not be negative, even if a or b are (since its negation would then be
 * a greater divisor). Implement the algorithm with %, floorMod, and a rem
 * function that produces the mathematical (non-negative) remainder. Which of
 * the three gives you the least hassle with negative values?
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex3 {
    
    public static int callCount;
    
    public static int gcd1(int a, int b) {
        ++callCount;
        if (b == 0) {
            assert(a >= 0);
            return a;
        }
        return gcd1(b, Math.abs(a%b)); 
    }
    
    public static int gcd2(int a, int b) {
        ++callCount;
        if (b == 0) {
            assert(a >= 0);
            return a;
        }
        
        return gcd2(b, Math.abs(Math.floorMod(a,b))); 
    }
    
    public static int gcd3(int a, int b) {
        ++callCount;
        if (b == 0) {
            assert(a >= 0);
            return a;
        }
        
        return gcd3(b, rem(a,b)); 
    }
    
    private static int rem(int a, int b) {
        return Math.abs(a) % Math.abs(b);
    }
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        callCount = 0;
        System.out.println("gcd1( 100,  75) : " + gcd1( 100,  75));
        System.out.println("gcd1( 100, -75) : " + gcd1( 100, -75));
        System.out.println("gcd1(-100,  75) : " + gcd1(-100,  75));
        System.out.println("gcd1(-100, -75) : " + gcd1(-100, -75));
        System.out.println("call count: " + callCount);

        callCount = 0;
        System.out.println();
        System.out.println("gcd2( 100,  75) : " + gcd2( 100,  75));
        System.out.println("gcd2( 100, -75) : " + gcd2( 100, -75));
        System.out.println("gcd2(-100,  75) : " + gcd2(-100,  75));
        System.out.println("gcd2(-100, -75) : " + gcd2(-100, -75));
        System.out.println("call count: " + callCount);
        
        callCount = 0;
        System.out.println();
        System.out.println("gcd3( 100,  75) : " + gcd3( 100,  75));
        System.out.println("gcd3( 100, -75) : " + gcd3( 100, -75));
        System.out.println("gcd3(-100,  75) : " + gcd3(-100,  75));
        System.out.println("gcd3(-100, -75) : " + gcd3(-100, -75));
        System.out.println("call count: " + callCount);
    }

}
