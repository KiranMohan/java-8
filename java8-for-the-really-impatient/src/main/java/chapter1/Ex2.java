package chapter1;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/*
 * Using the listFiles(FileFilter) and isDirectory methods of the java.io.File 
 * class, write a method that returns all subdirectories of a given directory. 
 * Use a lambda expression instead of a FileFilter object. Repeat with a method 
 * expression.
 */
public class Ex2 {

	public static void getSubDirectories(File parentDirectory, List<File> subDirectoriesList) {

		// using lambda block
		/*
		 * File[] subDirectories = parentDirectory.listFiles((d) -> d
		 * .isDirectory());
		 */

		// using method expressions
		File [] subDirectories = parentDirectory.listFiles(File::isDirectory);
		for(File d : subDirectories) {
			subDirectoriesList.add(d);
			getSubDirectories(d, subDirectoriesList);
		}

	}

	public static void main(String[] args) throws IOException {
		// Console console = System.console();
		// String directory = console.readLine("Enter directory to parse: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter directory to parse: ");
		System.out.flush();

		String directory = br.readLine();
		File parentDirectory = new File(directory);

		List<File> subDirectories = new LinkedList<File>();
		getSubDirectories(parentDirectory, subDirectories);
		
		for (File d : subDirectories) {
			System.out.println(d.getPath());
		}
		
	}

}
