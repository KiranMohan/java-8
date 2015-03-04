package chapter1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import common.Helper;

/*
 * Using the list(FilenameFilter) method of the java.io.File class, write a 
 * method that returns all files in a given directory with a given extension. 
 * Use a lambda expression, not a FilenameFilter. Which variables from the 
 * enclosing scope does it capture?
 */
public class Ex3 {
	public static File[] findFilesWithExtension(File directory, String extension) {

		File[] files = directory.listFiles(f -> {
			return f.isFile() && f.getPath().endsWith(extension);
		});
		return files;
	}

	public static void main(String[] args) throws IOException {
		// Console console = System.console();
		// String directory = console.readLine("Enter directory to parse: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Enter directory to search files with extension: ");
		System.out.flush();
		String directory = br.readLine();
		File parentDirectory = new File(directory);

		System.out.print("What is the extension? ");
		System.out.flush();
		String extension = br.readLine();

		List<File> subDirectories = new LinkedList<File>();
		Helper.getSubDirectories(parentDirectory, subDirectories);

		for (File d : subDirectories) {
			for (File f : findFilesWithExtension(d, extension)) {
				System.out.println(f.getPath());
			}
		}

	}
}
