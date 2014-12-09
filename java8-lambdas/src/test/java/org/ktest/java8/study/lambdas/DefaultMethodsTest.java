package org.ktest.java8.study.lambdas;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


interface IA {
	
	default String sayHi() {
		return "A says Hi";
	}
}

interface IB_ext_IA extends IA {
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

class E_ext_D_impl_IB extends D implements IB_ext_IA {
	
}

class F_impl_IC_IB implements IC,IB_ext_IA {
	// concrete implementation of sayHi() is required
	// as the duplicate default methods of C and B 
	// are in conflict
	@Override
    public String sayHi(){	
		return "F and " + IC.super.sayHi();
	}
}

class G_impl_IB implements IB_ext_IA{    
}

public class DefaultMethodsTest {
	
	@Test
	public void testDefaultMethods() {
		
		IB_ext_IA iB = new IB_ext_IA() { // interface B extends A
		};		
		IA iA = iB;
		
		assertThat(iB.sayHi(), is("B says Hi")); 
		assertThat(iA.sayHi(), is("B says Hi"));
		
		E_ext_D_impl_IB e_ext_D_impl_IB = new E_ext_D_impl_IB();  		
		assertThat(e_ext_D_impl_IB.sayHi(), is("D says Hi")); 
		
		F_impl_IC_IB f_impl_IC_IB = new F_impl_IC_IB(); 
		assertThat(f_impl_IC_IB.sayHi(), is("F and C says Hi"));
		
		G_impl_IB g_impl_IB = new G_impl_IB();
		assertThat(g_impl_IB.sayHi(), is("B says Hi"));
    }

}
