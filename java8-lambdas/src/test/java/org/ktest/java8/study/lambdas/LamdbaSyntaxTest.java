package org.ktest.java8.study.lambdas;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.ktest.java8.study.lambdas.datatypes.MyButton;
import org.ktest.java8.study.lambdas.datatypes.TelephoneVoice;

public class LamdbaSyntaxTest {

	private String[] names;

	/**
	 *
	 */
	public LamdbaSyntaxTest() {
		names = new String[] { "Ritchie", "Stroustrup", "Gosling", "Wall", "Odersky", "Hickey" };
	}

	@Test
	public void syntax() {

		Arrays.sort(names, (String first, String second) -> Integer.compare(first.length(), second.length()));
		System.out.printf("ordered by string length: %s\n", Arrays.toString(names));
		
		int prevLength=0;
		for(String name : names) {
			assertThat(name.length(), greaterThanOrEqualTo(prevLength));
		}
	}

	@Test
	public void syntaxTypeInference() {

		Arrays.sort(names, (first, second) -> Integer.compare(first.length(), second.length()));
		System.out.printf("ordered by string length: %s\n", Arrays.toString(names));
		
		int prevLength=0;
		for(String name : names) {
			assertThat(name.length(), greaterThanOrEqualTo(prevLength));
		}
	}

	@Test
	public void syntaxMultipleStatements() {

		Arrays.sort(names, (String first, String second) -> {
			if (first.length() < second.length()) {
				return -1;
			} else if (first.length() > second.length()) {
				return 1;
			} else {
				return 0;
			}
		});
		System.out.printf("ordered by string length: %s\n", Arrays.toString(names));
		
		int prevLength=0;
		for(String name : names) {
			assertThat(name.length(), greaterThanOrEqualTo(prevLength));
		}
	}

	@Test
	public void syntaxNoParameterFunction() {
		TelephoneVoice voice = new TelephoneVoice();

		Thread tSayHi = new Thread(() -> voice.sayHi());
		tSayHi.start();

		Thread tSayNumberDoesNotExist = new Thread(() -> voice.sayNumberDoesNotExist());
		tSayNumberDoesNotExist.start();

		Thread tSayNumberBusy = new Thread(() -> voice.sayNumberBusy());
		tSayNumberBusy.start();
	}

	@Test
	public void syntaxSingleParameterInferredType() {
		// Note: sample only. not for real world.
		MyButton b = new MyButton();

		// Old Ways
		//		b.addActionListener(
		//        new ActionListener() {
		//            public void actionPerformed(ActionEvent e) {
		//                System.out.println("Thanks for clicking");
		//            }
		//        });

		// New Way
		b.addActionListener(e -> System.out.println("Thanks for clicking"));
		b.keyPressed();
	}

}
