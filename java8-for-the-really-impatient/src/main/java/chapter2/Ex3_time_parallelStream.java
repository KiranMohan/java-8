/**
 * file       : Ex3_time_parallelStream.java
 * author     : Kiran Mohan
 * created on : 11-Mar-2015
 */
package chapter2;

import java.util.stream.Stream;

import common.Helper;

/**
 * Measure the difference when counting long words with a parallelStream instead
of a stream. Call System.currentTimeMillis before and after the call, and print the
difference. Switch to a larger document (such as War and Peace) if you have
a fast computer.
 * @author Kiran Mohan
 *
 */
public class Ex3_time_parallelStream {
    
    public static double timeStreamProcessing(Stream<String> words) {
        long startTime = System.currentTimeMillis();
        words.filter(w -> w.length() > 12).count();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] words = Helper.getWordsAsArray("src/main/resources/war_and_peace.txt");
        
        final int FUNC_REPEAT_COUNT = 100;
        long acc1 = 0, acc2 = 0;
        for (int i = 0; i < FUNC_REPEAT_COUNT ; ++i) {
            acc1 += timeStreamProcessing(Stream.of(words));
            acc2 += timeStreamProcessing(Stream.of(words).parallel());
        }

        System.out.println("Single Thread stream: " + acc1/FUNC_REPEAT_COUNT);
        System.out.println("Parallel Thread stream: " + acc2/FUNC_REPEAT_COUNT);
        
        // result: parallel streams slower???
        // Single Thread stream: 516
        // Parallel Thread stream: 700
        
    }
    
    

}
