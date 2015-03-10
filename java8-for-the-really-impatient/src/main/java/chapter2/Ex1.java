/**
 * file       : Ex1.java
 * author     : Kiran Mohan
 * created on : 10-Mar-2015
 */
package chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;

import common.Helper;

/**
 * Write a parallel version of the for loop in Section 2.1, “From Iteration to
 * Stream Operations,” on page 22. Obtain the number of processors. Make that
 * many separate threads, each working on a segment of the list, and total up
 * the results as they come in. (You don’t want the threads to update a single
 * counter. Why?)
 * 
 * @author Kiran Mohan
 *
 */
public class Ex1 {
    static final int NUM_OF_PROCESSORS = 4;

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] words = Helper.getWordsAsArray("src/main/resources/alice.txt");
        Predicate<String> filter = w -> w.length() > 6;

        System.out.println("count(single thead) : "
                + singleThreadCountWords(words, filter));
        System.out.println("count(parallelized) : "
                + parallelCountWords(words, filter));
    }

    public static int singleThreadCountWords(String[] words,
            Predicate<String> filter) {
        int count = 0;
        for (String w : words) {
            if (filter.test(w))
                count++;
        }
        return count;
    }

    /**
     * @param words
     */
    public static int parallelCountWords(String[] words,
            Predicate<String> filter) {

        ExecutorService executor = Executors
                .newFixedThreadPool(NUM_OF_PROCESSORS);
        List<Future<Integer>> results = new ArrayList<Future<Integer>>(
                NUM_OF_PROCESSORS);

        int sliceLength = words.length / NUM_OF_PROCESSORS;
        for (int from = 0; from < words.length; from += sliceLength) {
            int to = from + sliceLength;
            String[] slice = Arrays.copyOfRange(words, from,
                    to > words.length ? words.length : to);
            results.add(executor.submit(
                    () -> singleThreadCountWords(slice, filter)));
        }

        executor.shutdown();

        int count = 0;
        for (Future<Integer> result : results) {
            try {
                count += result.get();
            } catch (InterruptedException | ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return count;
    }

}
