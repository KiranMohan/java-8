package common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Helper {
	
	private Helper() {}
	
	public static String [] getWordsAsArray(String filename) {
		String [] words = null;
		// 1. filter
		try (Stream<String> lines = Files.lines(Paths.get(filename))) {
			
			words = lines.flatMap(t -> Stream.of(t.split("\\P{L}"))).toArray(String[]::new);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return words;
	}
	
	public static List<String> getWordsAsList(String filename) {
		return Arrays.asList(getWordsAsArray(filename));
	}
}
