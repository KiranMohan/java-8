/**
 * file       : Ex11.java
 * author     : Kiran Mohan
 * created on : 10-Mar-2015
 */
package chapter1;


/**
 * Suppose you have a class that implements two interfaces I and J, each of
which has a method void f(). Exactly what happens if f is an abstract, default,
or static method of I and an abstract, default, or static method of J? Repeat
where a class extends a superclass S and implements an interface I, each
of which has a method void f().
 * @author Kiran Mohan
 *
 */
public class Ex11 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        X x = new X();
        x.fAbstract();  // prints X fAbstract()
        
        x.fStatic1();   // prints X fStatic1()
        X.fStatic2();   // prints X fStatic2()
        I.fStatic1();   // prints I fStatic1()
        I.fStatic2();   // prints I fStatic2()
        J.fStatic1();   // prints J fStatic1()
        J.fStatic2();   // prints J fStatic2()
        
        x.fDefault();   //prints X fDefault() calling I fDefault()

        System.out.println("-----------------");
        Y y = new Y();
        
        y.fAbstract();  // prints Y fAbstract()
        
        Y.fStatic1();   // prints K fStatic1()
        Y.fStatic2();   // prints Y fStatic2()
        K.fStatic1();   // prints K fStatic1()
        K.fStatic2();   // prints K fStatic2()
        I.fStatic1();   // prints I fStatic1()
        I.fStatic2();   // prints I fStatic2()
        y.fDefault();   // prints K fDefault();
        
    }

}


interface I {
    
    void fAbstract();
    
    static void fStatic1(){
        System.out.println("I fStatic1()");
    }
    
    static void fStatic2(){
        System.out.println("I fStatic2()");
    }
    
    default void fDefault() {
        System.out.println("I fDefault()");
    }
}

interface J {
    
    void fAbstract();
    
    static void fStatic1() {
        System.out.println("J fStatic1()");
    }
    
    static void fStatic2() {
        System.out.println("J fStatic2()");
    }
    
    default void fDefault() {
        System.out.println("J fDefault()");
    }
}

class X implements I, J {
    
    public void f() {
        System.out.println("Class X");
    }
    
    /* (non-Javadoc)
     * @see chapter1.I#fAbstract()
     */
    @Override
    public void fAbstract() {
        System.out.println("X fAbstract()");
    }
    
    public void fStatic1() {
        System.out.println("X fStatic1()");
    }
    
    public static void fStatic2() {
        System.out.println("X fStatic2()");
    }
    
    // duplicate default method fDefault() inherited from I & J must be resolved
    // by implementing class
    public void fDefault() {
        System.out.print("X fDefault() calling ");     
        I.super.fDefault();
    }
    
}

abstract class K {
    
    public abstract void fAbstract();
    
    public static void fStatic1() {
        System.out.println("K fStatic1()");
    }
    
    public static void fStatic2() {
        System.out.println("K fStatic2()");
    }
    
    public void fDefault() {
        System.out.println("K fDefault()");
    }
}

class Y extends K implements I {
    
    public void f() {
        System.out.println("Class Y");
    }
    
    /* (non-Javadoc)
     * @see chapter1.I#fAbstract()
     */
    @Override
    public void fAbstract() {
        System.out.println("Y fAbstract()");
    }

//    This instance method cannot override the static method from K
//    public void fStatic1() {
//        System.out.println("Y fStatic1()");
//    }
    
    public static void fStatic2() {
        System.out.println("Y fStatic2()");
    }
    
//    default method from superclass is inherited even when same method has 
//    default implementation in inherited interface.
//    public void fDefault() {
//        System.out.print("X fDefault() calling ");     
//        I.super.fDefault();
//    }
    
}