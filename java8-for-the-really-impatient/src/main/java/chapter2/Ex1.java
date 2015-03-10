/**
 * file       : Ex1.java
 * author     : Kiran Mohan
 * created on : 10-Mar-2015
 */
package chapter2;

import common.Helper;

/**
 * Write a parallel version of the for loop in Section 2.1, “From Iteration to
Stream Operations,” on page 22. Obtain the number of processors. Make that
many separate threads, each working on a segment of the list, and total up
the results as they come in. (You don’t want the threads to update a single
counter. Why?)
 * @author Kiran Mohan
 *
 */
public class Ex1 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] words = Helper.getWordsAsArray("src/main/resources/alice.txt");
        int count = 0;        
        for (String w : words) {
            if (w.length() > 12)
                count++;
        }
        System.out.println(count);
    }

}
