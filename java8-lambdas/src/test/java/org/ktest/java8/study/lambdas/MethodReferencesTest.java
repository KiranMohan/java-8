package org.ktest.java8.study.lambdas;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.function.BiFunction;

import org.junit.Test;
import org.ktest.java8.study.lambdas.datatypes.GreeterDerived;
import org.ktest.java8.study.lambdas.datatypes.MyButton;

public class MethodReferencesTest {

	@Test
	public void objectInstanceMethod() {
		MyButton button = new MyButton();
		button.addActionListener(System.out::println); // same as (event) -> System.out.println(event)
		button.keyPressed();
		button.keyPressed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "blah"));
	}
	
	public static Double applyFunction(Double input1, Double input2, BiFunction<Double, Double, Double> function){
	    return function.apply(input1, input2);
	}

	@Test
	public void classStaticMethod() {
		BiFunction<Double, Double, Double> power;
		// old way as anonymous class
		//power = new BiFunction<Double, Double, Double>() {
		//		@Override
		//		public Double apply(Double t, Double u) {
		//			return Math.pow(t, u);
		//		}
		//};

		// as lambda expression without method references 
		//power = (x,y) -> Math.pow(x,y); 	
		
		// with method references
		power = Math::pow; // more concise
		
		System.out.println(applyFunction(2.0, 4.0, power));
		System.out.println(applyFunction(2.0, 4.0, Math::pow));
	    System.out.println(applyFunction(2.0, 4.0, Math::max));
	}

	@Test
	public void classInstanceMethod() {
		String[] names = new String[] { "Ritchie", "Stroustrup", "Gosling", "Wall", "Odersky", "Hickey" };

		// old way
		//		Arrays.sort(names, new Comparator<String>() {
		//            @Override
		//            public int compare(String x, String y) {	            
		//	            return x.compareToIgnoreCase(y);
		//            }
		//		});

		Arrays.sort(names, String::compareToIgnoreCase); // same as (x, y) -> x.compareToIgnoreCase(y)
		System.out.println(Arrays.toString(names));
	}

	@Test
	public void referencesToSuperAndThis() {
		GreeterDerived greeter = new GreeterDerived();
		greeter.greet();
	}

}
