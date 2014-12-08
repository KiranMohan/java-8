package org.ktest.java8.study.lambdas;

import org.junit.Test;

class MutableClass {
	int mutableInt = 10;
}

public class VariableScopeTest {

	@Test
	public void closureAndScope() {
		String text = "text";
		final int count = 10;
		int mutableInt = 20;
		
		Runnable r = () -> {
			for (int i = 0; i < count; i++) { 	// free variable - count
				System.out.println(text +		// free variable - text 
						", " + mutableInt) ;		
				//mutableInt++;					// cannot change, should be effectively final
				Thread.yield();				
			}
		};
		
		new Thread(r).start();
	}
	
	
	@Test
	public void mutableSharedObject() {
		MutableClass m = new MutableClass();
		
		Runnable r = () -> {
			for (int i = 0; i < 10; i++) { 	
				System.out.println(m.mutableInt) ;		
				//m = new MutableClass();	// cannot change, reference m should be effectively final
				m.mutableInt++; 			// ok. but not threadsafe
				Thread.yield();				
			}
		};
		
		new Thread(r).start();
		
	}

}
