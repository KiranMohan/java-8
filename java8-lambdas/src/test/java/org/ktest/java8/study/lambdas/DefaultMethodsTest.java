package org.ktest.java8.study.lambdas;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


interface IA {
	
	default String sayHi() {
		return "A says Hi";
	}
}

interface IB_ext_A extends IA {
	@Override
    default String sayHi() {
		return "B says Hi";
	}
}

interface IC {
	default String sayHi() {
		return "C says Hi";
	}
}

class D {
	public String sayHi() {
		return "D says Hi";
	}
}

class E_ext_D_impl_IB extends D implements IB_ext_A {
	
}

class F_impl_IC_IB implements IC,IB_ext_A {
	// concrete implementation of sayHi() is required
	// as the duplicate default methods of C and B 
	// are in conflict
	@Override
    public String sayHi(){	
		return "F and " + IC.super.sayHi();
	}
}

public class DefaultMethodsTest {
	
	@Test
	public void testDefaultMethods() {
		
		IB_ext_A iB = new IB_ext_A() { // interface B extends A
		};		
		IA iA = iB;
		
		assertThat(iB.sayHi(), is("B says Hi")); 
		assertThat(iA.sayHi(), is("B says Hi"));
		
		E_ext_D_impl_IB e_ext_D_impl_IB = new E_ext_D_impl_IB();  		
		assertThat(e_ext_D_impl_IB.sayHi(), is("D says Hi")); 
		
		F_impl_IC_IB f_impl_IC_IB = new F_impl_IC_IB(); 
		assertThat(f_impl_IC_IB.sayHi(), is("F and C says Hi"));  
    }

}
