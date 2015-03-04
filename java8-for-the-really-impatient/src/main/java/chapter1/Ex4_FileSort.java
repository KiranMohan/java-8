/**
 * file       : Ex4.java
 * author     : Kiran Mohan
 * created on : 04-Mar-2015
 */
package chapter1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import common.Helper;

/**
 * Given an array of File objects, sort it so that the directories come before 
 * the files, and within each group, elements are sorted by path name. Use a 
 * lambda expression, not a Comparator.
 * @author Kiran Mohan
 *
 */
public class Ex4_FileSort {

    public static File[] sort(File[] files) {
        Arrays.sort(files, (f1, f2) -> {
            return f1.getPath().compareToIgnoreCase(f2.getPath());
        });
        return files;
    }

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter directory to search files with extension: ");
        System.out.flush();
        String directory = br.readLine();
        File parentDirectory = new File(directory);

        List<File> result = new LinkedList<>();
        Helper.getDirectoryContents(parentDirectory, result);

        for (File f : Ex4_FileSort.sort(result.toArray(new File[0]))) {
            System.out.println(f.getPath());
        }
    }

}
