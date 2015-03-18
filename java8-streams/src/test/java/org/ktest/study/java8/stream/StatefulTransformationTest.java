package org.ktest.study.java8.stream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class StatefulTransformationTest {

	@Test
    public void distinct() {
		List<String> uniqueWords = Stream.of("merrily", "merrily", "merrily", "gently")
		                                   .distinct()
		                                   .collect(Collectors.toList());

		System.out.println(uniqueWords);
		assertThat(uniqueWords, containsInAnyOrder("merrily", "gently"));
	}
	
	@Test
	public void sorted() {
		try(Stream<String> lines = Files.lines(Paths.get("../alice.txt"))){
			List<String> words = lines.flatMap(t -> Stream.of(t.split("\\P{L}")))
										.filter((w)-> w.length() > 1)
										.distinct()
										.sorted()  // sorted(Comparator<T> comp) also available
										.collect(Collectors.toList());

			String prev = "";
			for(String w : words) {
			    assertThat(w, greaterThan(prev));
			    prev = w;
			}

		} catch (IOException e) {
	        e.printStackTrace();
        }

	}

}
