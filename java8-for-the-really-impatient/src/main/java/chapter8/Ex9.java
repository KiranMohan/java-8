/**
 * file       : Ex9.java
 * author     : "Kiran Mohan"
 * created on : Mar 22, 2015
 */
package chapter8;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Write methods that turn a Scanner into a stream of words, lines, integers, or
 * double values. Hint: Look at the source code for BufferedReader.lines.
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex9 {
    
    public static Stream<String> getWordStream(Scanner scanner) {
        Iterator<String> iter = new Iterator<String>() {

            @Override
            public boolean hasNext() {
                 return scanner.hasNext();
            }

            @Override
            public String next() {
                return scanner.next();
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(Paths.get("src/main/resources/alice.txt"))){
            getWordStream(scanner).forEach(System.out::println);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("--- Program End ---");
    }

}
