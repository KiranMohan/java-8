/**
 * file       : ConcreteGreeter.java
 * author     : Kiran Mohan
 * created on : 23-Nov-2014
 */
package org.ktest.java8.study.lambdas.datatypes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GreeterDerived extends GreeterBase {
	
	public void sayHello() {
		System.out.println("Hello Dude");
	}
	
	public Integer get100(){
		return 100;
	}
	
	@Override
    public void greet(){
		Thread  t = new Thread(super::greet); // using super in method references
		t.start();
		Thread t2 = new Thread(this::sayHello);
		t2.start();
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Integer> result = executor.submit(this::get100);
		try {
	        System.out.println(result.get());
        } catch (Exception e) {
	        e.printStackTrace();
        } 
	}
}