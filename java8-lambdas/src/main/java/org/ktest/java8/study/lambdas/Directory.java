package org.ktest.java8.study.lambdas;

import java.io.File;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Directory {
	
	private static void subDirectories(File root, List<String> result) {
		File[] subdirs = root.listFiles(File::isDirectory);		
		for(File f : subdirs) {
			result.add(f.getAbsolutePath());
			subDirectories(f,result);
		}
	}
	
	public static List<String> subDirectories(String rootDirectory){
		File file = Paths.get(rootDirectory).toFile();		
		List<String> dirs = new LinkedList<String>();
		subDirectories(file, dirs);
		
		return dirs; 
	}

	public static void main(String[] args) {
			
		subDirectories("C:\\users\\someuser\\Temp")
			.stream()
			.forEach(System.out::println);
	}

}
