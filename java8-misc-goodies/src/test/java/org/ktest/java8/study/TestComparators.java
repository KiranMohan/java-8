package org.ktest.java8.study;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.Matchers.*;

class TestPerson {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    /**
     * @param firstName
     * @param middleName
     * @param lastName
     */
    public TestPerson(String firstName, String middleName, String lastName) {
        super();
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }
    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TestPerson " + String.join(" ", firstName, middleName, lastName); 
    }
    
    
    
}

public class TestComparators {

	@Test
	public void producingComparators() {
		String[] names = new String[] { "Ritchie", "Stroustrup", "Gosling", "Wall", "Odersky", "Hickey" };
		
		//Arrays.sort(names, (first, second) -> Integer.compare(first.length(), second.length()));		
		Arrays.sort(names, Comparator.comparing(String::length));  // comparing() is a static method in Comparator Interface		
		System.out.printf("ordered by string length: %s\n", Arrays.toString(names));
		
		int prevLength=0;
		for(String name : names) {
			assertThat(name.length(), greaterThanOrEqualTo(prevLength));
		}
	}
	
	@Test
	public void test_Comparator_Chaining() {
	    List<TestPerson> people = Arrays.asList(new TestPerson("A", "B", "C"),
	                                            new TestPerson("D", "E", "F"),
	                                            new TestPerson("A", "E", "F"),
	                                            new TestPerson("D", "E", "G"));
	    people.sort(Comparator.comparing(TestPerson::getFirstName)
	                          .thenComparing(TestPerson::getMiddleName)
	                          .thenComparing(TestPerson::getLastName));
	    System.out.println(people);
	}

}
