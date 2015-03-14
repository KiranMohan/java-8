/**
 * file       : Ex7_Comparator.java
 * author     : "Kiran Mohan"
 * created on : Mar 14, 2015
 */
package chapter3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;



/**
 * @author "Kiran Mohan"
 *
 */
public class Ex7_Comparator {
    
    static enum Mode {
        COMPARE_REVERSED,
        COMPARE_CASE_INSENSITIVE,
        COMPARE_SPACE_INSENSITIVE
    }
    /**
     * Write a method that generates a Comparator<String> that can be normal or reversed, 
     * case-sensitive or case-insensitive, space-sensitive or space-insensitive, or any 
     * combination thereof. Your method should return a lambda expression
     * @param flags
     * @return
     */
    public static Comparator<String> getComparator(EnumSet<Mode> flags) {
        return (s1, s2) -> {
            if (flags.contains(Mode.COMPARE_REVERSED)) {
                String temp = s1;
                s1 = s2;
                s2 = temp;
            }
            if (flags.contains(Mode.COMPARE_SPACE_INSENSITIVE)) {
                s1 = s1.replaceAll("\\s", "");
                s2 = s2.replaceAll("\\s", "");
            }
            if (flags.contains(Mode.COMPARE_CASE_INSENSITIVE)) {
                return s1.compareToIgnoreCase(s2);
            }
            return s1.compareTo(s2);
        };
    }
    
    public static void main(String [] args){
        Comparator<String> comparator = getComparator(EnumSet.of(Mode.COMPARE_CASE_INSENSITIVE, 
                                                                 Mode.COMPARE_SPACE_INSENSITIVE));
        
        String[] names = new String[] { "dennis Ritchie", "Bjarne Stroustrup", "James Gosling", 
                                        "Larry Wall", "Martin Odersky", "Rich Hickey" };
        Arrays.sort(names, comparator);
        System.out.println(Arrays.toString(names));        
    }
}


