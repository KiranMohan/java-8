package org.ktest.java8.study;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestComparators {

	@Test
	public void producingComparators() {
		String[] names = new String[] { "Ritchie", "Stroustrup", "Gosling", "Wall", "Odersky", "Hickey" };
		
		//Arrays.sort(names, (first, second) -> Integer.compare(first.length(), second.length()));		
		Arrays.sort(names, Comparator.comparing(String::length));  // comparing() is a static method in Comparator Interface		
		System.out.printf("ordered by string length: %s\n", Arrays.toString(names));
		
		int prevLength=0;
		for(String name : names) {
			assertThat(name.length(), greaterThanOrEqualTo(prevLength));
		}
	}

}
