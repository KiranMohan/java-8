package org.ktest.study.java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.event.ListSelectionEvent;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class StreamCreationTest {

	private final String contents = "It flowed gently down the stream";
	private final String[] wordsArray = contents.split("\\P{L}");
	private final List<String> wordsList = Arrays.asList(wordsArray);

	@Test
	public void streamFromCollection() {

		Stream<String> wordStream = wordsList.stream();	

		String[] arrayFromStream = wordStream.toArray(String[]::new);
		System.out.println(Arrays.toString(arrayFromStream));
		assertThat(arrayFromStream, is(arrayContainingInAnyOrder(wordsArray)));
	}

	@Test
	public void streamFromArray() {
		String[] arrayFromStream;

		// --------------------- 1. Array to Stream --------------------------
		Stream<String> wordStream = Stream.of(wordsArray);

		arrayFromStream = wordStream.toArray(String[]::new);
		assertThat(arrayFromStream, is(arrayContainingInAnyOrder(wordsArray)));		
		// -------------------------------------------------------------------
		
		// -------------- 2. Array to Stream using Arrays.stream--------------
		wordStream = Arrays.stream(wordsArray, 3, wordsArray.length);
		
		arrayFromStream = wordStream.toArray(String[]::new);
		assertThat(arrayFromStream, arrayWithSize(wordsArray.length - 3));
		assertThat(Arrays.asList(arrayFromStream), everyItem(isIn(wordsArray)));
		// -------------------------------------------------------------------
	}
	
	@Test
	public void generateStream(){
		// --------------------- Stream.generate() ---------------------------
		Stream<String> echos = Stream.generate(() -> "echos"); 	// infinite stream
		
		List<String> echosList = echos.limit(10).collect(Collectors.toList());		// limit the stream to 10
		assertThat(echosList, hasSize(10));
		assertThat(echosList, everyItem(is("echos")));
		// -------------------------------------------------------------------
	}
	
	@Test
	public void iterateStream(){
		// --------------------- Stream.iterate() ----------------------------
		Stream<Integer> intSeqStream = Stream.iterate(1, x -> x + 2);	// infinite stream
		
		Integer[] intSeqArray = intSeqStream.limit(10).toArray(Integer[]::new);
		
		// assert 10 elements
		assertThat(intSeqArray, arrayWithSize(10));	
		
		for(int i = 0; i < intSeqArray.length; ++i ){
			assertThat(intSeqArray[i], is(2*i + 1));
		}
		System.out.println(Arrays.toString(intSeqArray));
		// -------------------------------------------------------------------
	}
	

	@Test
	public void testAutoCloseable() {
		IsResourceClosed isResourceClosed = new IsResourceClosed(); 
		
		try (Stream<String> lines= Files.lines(Paths
		        .get("../alice.txt"))) {
			
			lines.onClose(() -> isResourceClosed.setClosed()); 
			// lines.limit(10).forEach(System.out::println);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertTrue(isResourceClosed.isClosed());
	}
	
}

class IsResourceClosed{
	private boolean closed = false;
	public synchronized boolean isClosed() {
		return isClosed(500); // 500ms timeout
	}
	public synchronized boolean isClosed(int timeOut) {
		for(int i = 0 ; !closed || i < 3; i++) {
			try {
                wait(timeOut);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
		return closed;
	}
	
	synchronized void setClosed() {
		closed = true;
		notify();
	}
}
