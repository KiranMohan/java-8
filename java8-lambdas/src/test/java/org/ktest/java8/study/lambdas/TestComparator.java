/**
 * file       : TestComparator.java
 * author     : Kiran Mohan
 * created on : 26-Feb-2015
 */
package org.ktest.java8.study.lambdas;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Stream;






import org.junit.Test;

enum Mode {
    COMPARE_REVERSED,
    COMPARE_CASE_INSENSITIVE,
    COMPARE_SPACE_INSENSITIVE
}

class Person {
    private String firstName;
    private String lastName;
    public Person(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }
    
}

/**
 * @author Kiran Mohan
 *
 */
public class TestComparator {

    /**
     * Write a method that generates a Comparator<String> that can be normal or reversed, 
     * case-sensitive or case-insensitive, space-sensitive or space-insensitive, or any 
     * combination thereof. Your method should return a lambda expression
     * @param flags
     * @return
     */
    public static Comparator<String> getComparator(Set<Mode> flags) {
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
    
    @Test
    public void testGetComparator(){
        Comparator<String> comparator = getComparator(EnumSet.of(Mode.COMPARE_CASE_INSENSITIVE, Mode.COMPARE_SPACE_INSENSITIVE));
        
        String[] names = new String[] { "Dennis Ritchie", "Bjarne Stroustrup", "James Gosling", "Larry Wall", "Martin Odersky", "Rich Hickey" };
        Arrays.sort(names, comparator);
        System.out.println(Arrays.toString(names));        
    }
    
    /**
     * Write a method lexicographicComparator(String... fieldNames) that yields 
     * a comparator that compares the given fields in the given order. For example, 
     * a lexicographicComparator("lastname", "firstname") takes two objects and, 
     * using reflection, gets the values of the lastname field. If they are 
     * different, return the difference, otherwise move on to the firstname field. 
     * If all fields match, return 0
     * @param fieldNames
     * @return
     */
    public static <T> Comparator<T> lexicographicComparator(String... fieldNames) {

        return (o1, o2) -> {
            if (o1 == o2) return 0;
            int cmp = 0;
            try {
                for (String fieldName : fieldNames) {
                    String value1 = null, value2 = null;

                    Field field1 = o1.getClass().getDeclaredField(fieldName);
                    field1.setAccessible(true);
                    value1 = (String) field1.get(o1);

                    Field field2 = o2.getClass().getDeclaredField(fieldName);
                    field2.setAccessible(true);
                    value2 = (String) field2.get(o2);

                    if ((cmp = value1.compareTo(value2)) != 0)
                        break;
                }
            } catch (NoSuchFieldException|IllegalAccessException e) {
                e.printStackTrace();
            }
            return cmp;
        };
    }
    
    @Test
    public void testLexicographicComparator() throws Exception {
        Person p1 = new Person("11Name", "12Name");
        Person p2 = new Person("21Name", "22Name");
        
        Comparator<Person> comparator = lexicographicComparator("firstName", "lastName");
        
        int cmp = comparator.compare(p2, p1);
        System.out.println("cmp");
        assertThat(cmp, greaterThan(0));
    }

}
