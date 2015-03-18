package org.ktest.java8.study.lambdas;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;
import org.ktest.java8.study.lambdas.datatypes.TelephoneVoice;

public class DeferredExecutionNewWayTest {
	
	@Test
	public void threads() {
		TelephoneVoice voice = new TelephoneVoice();
		
		Thread tSayHi = new Thread(voice::sayHi);
		tSayHi.start();
		
		Thread tSayNumberDoesNotExist = new Thread(voice::sayNumberDoesNotExist);
		tSayNumberDoesNotExist.start();
		
		Thread tSayNumberBusy = new Thread(voice::sayNumberBusy);
		tSayNumberBusy.start();
	}
	

	@Test
	public void passingComparators(){
		String [] names = {"Ritchie", "Stroustrup", "Gosling", "Wall", "Odersky", "Hickey"};				
		
		// ---------------------------------------------------------------------------------
		Arrays.sort(names, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
		// OR better
		// Arrays.sort(names, Comparator.comparing(String::length));
		System.out.printf("ordered by string length: %s\n", Arrays.toString(names));
		
		int prevLength=0;
		for(String name : names) {
			assertThat(name.length(), greaterThanOrEqualTo(prevLength));
		}
		
		// ----------------------------------------------------------------------------------
		Arrays.sort(names, String::compareTo); 
		// note: example only. string natural ordering doesn't require additional comparator.
		System.out.printf("natural order : %s\n", Arrays.toString(names));
		
		String prevName="";
		for(String name : names) {
			assertThat(name, greaterThanOrEqualTo(prevName));
		}
	}
}
