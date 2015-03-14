/**
 * file       : Ex9_LexicographicalComparator.java
 * author     : "Kiran Mohan"
 * created on : Mar 14, 2015
 */
package chapter3;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Field;
import java.util.Comparator;

import org.junit.Test;

/**
 * Write a method lexicographicComparator(String... fieldNames) that yields a
 * comparator that compares the given fields in the given order. For example, a
 * lexicographicComparator("lastname", "firstname") takes two objects and, using
 * reflection, gets the values of the lastname field. If they are different,
 * return the difference, otherwise move on to the firstname field. If all
 * fields match, return 0
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex9_LexicographicalComparator {
    /**
     * @param fieldNames
     * @return
     */
    public static <T> Comparator<T> lexicographicComparator(String... fieldNames) {

        return (o1, o2) -> {
            if (o1 == o2)
                return 0;
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
            } catch (NoSuchFieldException | IllegalAccessException e) {
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