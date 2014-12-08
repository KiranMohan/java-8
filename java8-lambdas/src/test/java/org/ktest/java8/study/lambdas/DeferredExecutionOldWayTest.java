package org.ktest.java8.study.lambdas;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;
import org.ktest.java8.study.lambdas.datatypes.TelephoneVoice;
import org.ktest.java8.study.lambdas.datatypes.TelephoneVoiceRunnable;

public class DeferredExecutionOldWayTest {

	@Test
	public void usingConcreteClass() {
		Thread t = new Thread(new TelephoneVoiceRunnable());
		t.start();
	}
	
	@Test
	public void usingAnonymousClass(){
		TelephoneVoice voice = new TelephoneVoice();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				voice.sayHi();
			}
		});
		t.start();
	}
	
	@Test
	public void passingComparators(){
		
		String [] names = {"Ritchie", "Stroustrup", "Gosling", "Wall", "Odersky", "Hickey"};
		
		class LengthComparator implements Comparator<String>{
            @Override
            public int compare(String o1, String o2) {
	            return Integer.compare(o1.length(), o2.length());
            }
			
		};
		
		Arrays.sort(names, new LengthComparator());
		
		int prevLength=0;
		for(String name : names) {
			assertThat(name.length(), greaterThanOrEqualTo(prevLength));
		}
	}	

}
