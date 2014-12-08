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
		MyButton b = new MyButton();
		b.addActionListener(System.out::println); // same as x -> System.out.println(x)
		b.keyPressed();
		b.keyPressed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "blah"));
	}

	@Test
	public void classStaticMethod() {
		BiFunction<Double, Double, Double> power;
		// old way
		//power = new BiFunction<Double, Double, Double>() {
		//		@Override
		//		public Double apply(Double t, Double u) {
		//			return Math.pow(t, u);
		//		}
		//};

		//power = (x,y) -> Math.pow(x,y); 		
		power = Math::pow; // more concise
		System.out.println(power.apply(2.0, 2.0));
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
