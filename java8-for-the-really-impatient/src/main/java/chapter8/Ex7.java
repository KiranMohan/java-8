/**
 * file       : Ex7.java
 * author     : "Kiran Mohan"
 * created on : Mar 22, 2015
 */
package chapter8;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author "Kiran Mohan"
 *
 */
public class Ex7 {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> ints = random.ints(10, -100, 100) // number b/w 0 and 100
                                   .mapToObj(Integer::valueOf)
                                   .collect(Collectors.toList());
        ints.add(3, null);
        ints.add(7, null);
        
        ints.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
        System.out.println(ints);
    }

}
