/**
 * file       : SubstreamAndConcatenationTest.java
 * author     : Kiran Mohan
 * created on : 29-Nov-2014
 */
package org.ktest.study.java8.stream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * @author Kiran Mohan
 *
 */
public class SubstreamAndConcatenationTest {

	@Test
	public void limit() {
		List<Double> randoms = Stream.generate(Math::random)
				                     .limit(100)
				                     .collect(Collectors.toList());

		assertThat(randoms, hasSize(100));
	}

	@Test
	public void skip() {
		List<Integer> ints = Stream.iterate(1, x -> ++x)
				                   .skip(10)    // skip the first 10 elements
				                   .limit(100)
				                   .collect(Collectors.toList());
		assertThat(ints.get(0), is(11));
		assertThat(ints, hasSize(100));
	}
	
	@Test
	public void concat() {
		List<Character> charList = Stream.concat("Hello".chars().mapToObj(c-> (char)c), 
												 "World".chars().mapToObj(c-> (char)c))
										 .collect(Collectors.toList());
		assertThat(charList, everyItem(isIn(Arrays.asList('H','e','l','o','W','r','d'))));
	}
	
	@Test
	public void peek() {
	       List<Integer> ints = Stream.iterate(1, x -> ++x)
	               .peek(e -> System.out.println("\tb " + e)) // before skip
                   .skip(10)    
                   .peek(e -> System.out.println("a " + e)) // after skip
                   .limit(10)
                   .collect(Collectors.toList());
            assertThat(ints.get(0), is(11));
            assertThat(ints, hasSize(10));
	}

}
