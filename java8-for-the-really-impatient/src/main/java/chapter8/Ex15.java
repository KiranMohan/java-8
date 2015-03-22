/**
 * file       : Ex15.java
 * author     : "Kiran Mohan"
 * created on : Mar 22, 2015
 */
package chapter8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Using Files.lines and Pattern.asPredicate, write a program that acts like the
 * grep utility, printing all lines that contain a match for a regular
 * expression
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex15 {
    
    public static List<String> grep(Path file, String regex) {
        List<String> matchedLines = null;
        try(Stream<String> lines = Files.lines(file)) {
            matchedLines = lines.filter(Pattern.compile(regex).asPredicate())
                                .collect(Collectors.toList());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return matchedLines;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        grep(Paths.get("src/main/resources/alice.txt"), "[Aa]lice")
            .forEach(System.out::println);
    }

}
