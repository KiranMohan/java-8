package common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Helper {

    private Helper() {
    }

    /**
     * Get words from a give text file and return as an {@code String} array
     */
    public static String[] getWordsAsArray(String filename) {
        String[] words = null;
        // 1. filter
        try (Stream<String> lines = Files.lines(Paths.get(filename))) {

            words = lines.flatMap(t -> Stream.of(t.split("\\P{L}"))).toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    /**
     * Get words from a give text file and return as an {@code List<String>}
     */
    public static List<String> getWordsAsList(String filename) {
        return Arrays.asList(getWordsAsArray(filename));
    }

    /**
     * Find subdirectories for the given parent directory and return it in the
     * subDirectoriesList
     * @param parentDirectory 
     * @param subDirectoriesList the subdirectories found are returned in this 
     *                           list
     */
    public static void getSubDirectories(File parentDirectory, List<File> subDirectoriesList) {
        File[] subDirectories = parentDirectory.listFiles(File::isDirectory);
        for (File d : subDirectories) {
            subDirectoriesList.add(d);
            getSubDirectories(d, subDirectoriesList);
        }
    }

}
