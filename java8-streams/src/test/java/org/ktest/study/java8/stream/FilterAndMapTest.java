package org.ktest.study.java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class FilterAndMapTest {

	@Test
	public void filterBigWords(){
		List<String> bigWords = wordList.stream()
										.filter(w -> w.length() >= 6)
										.collect(Collectors.toList());
			bigWords.forEach(s -> assertThat(s.length(),greaterThanOrEqualTo(6)));
	}
	
	@Test
	public void map() {
		// create a list of the first letters of the given words
		List<String> names = Arrays.asList("Ritchie", "Stroustrup", "Gosling", "Wall", "Odersky", "Hickey");
		
		List<Character> firstLetterList = names.stream()
										   .map(s -> s.charAt(0))
										   .collect(Collectors.toList());
		
		assertThat(firstLetterList,contains('R','S','G','W','O','H'));
	}
	
	@Test
	public void flatMap() {
		// create a list of the characters of the given words
		List<String> names = Arrays.asList("Ritchie", "Stroustrup", "Gosling", "Wall", "Odersky", "Hickey");
		
		Stream<Character> charStream = names.stream()
				                         .flatMap(s -> s.chars().mapToObj(c -> Character.valueOf((char) c)));
		Set<Character> charSet = charStream.collect(Collectors.toSet());
		
		/*
		 * Eclipse bug: eclipse is unable to resolve the return types here correctly.
		 Set<Character> charSet = names.stream()
                .flatMap(s -> s.chars().mapToObj(c->(char)c))
                .collect(Collectors.toSet());
         */

		// OR
		
//		Set<Character> charStream = names.stream()
//										 .flatMapToInt(s -> s.chars())
//										 .mapToObj(c -> (char)c)
//										 .collect(Collectors.toSet());
		System.out.println(charSet);
	}
	
	
	@BeforeClass
	public static void prepareWordList() {
		// 1. filter
		try (Stream<String> lines = Files.lines(Paths.get("../alice.txt"))) {
			
			wordList = lines.flatMap(t -> Stream.of(t.split("\\P{L}")))
							.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static List<String> wordList;

}
