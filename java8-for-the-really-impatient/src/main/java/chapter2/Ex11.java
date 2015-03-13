/**
 * file       : Ex11.java
 * author     : "Kiran Mohan"
 * created on : Mar 13, 2015
 */
package chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import common.Helper;

/**
 * It should be possible to concurrently collect stream results in a single ArrayList ,
instead of merging multiple array lists, provided it has been constructed with
the stream’s size, since concurrent set operations at disjoint positions
are threadsafe. How can you achieve that?
 * @author "Kiran Mohan"
 *
 */
public class Ex11 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // not a perfect answer.
        List<Integer> l1 = Helper.getIntegerSequence( 1, 1, 10);
        List<Integer> l2 = Helper.getIntegerSequence(11, 1, 10);
        List<Integer> l3 = Helper.getIntegerSequence(21, 1, 10);
        List<Integer> l4 = Helper.getIntegerSequence(31, 1, 10);
        
        AtomicInteger i = new AtomicInteger(0);
        List<Integer> lFinal = new ArrayList<Integer>(40);
        Stream.of(l1,l2,l3,l4).parallel().forEach(l -> {
            AtomicInteger j = new AtomicInteger(i.get() * 10);
            for(Integer x: l) {
                lFinal.add(j.getAndIncrement(), x);
            }
            i.incrementAndGet();
        });

        System.out.println(lFinal);
        
    }

}
