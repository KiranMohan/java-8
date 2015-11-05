package org.ktest.java8.study.lambdas;

import org.junit.Test;
import org.ktest.java8.study.lambdas.datatypes.MyButton;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.ktest.java8.study.lambdas.ComponentFactory.ComponentType.BUTTON;

public class ConstructorReferencesTest {

	@Test
	public void constructorReferences() {
		String [] labels = {"copy", "paste", "delete"};		
		
		Stream<MyButton> map = Stream.of(labels).map(MyButton::new); // Constructor reference.
		List<MyButton> myButtons = map.collect(Collectors.toList());
		
		myButtons.forEach(System.out::println);
		assertThat(myButtons.stream().map(MyButton::getLabel).toArray(), 
				   is(arrayContainingInAnyOrder(labels)));
		
	}
	
	@Test
	public void arrayConstructorReference(){
		List<String> labelsList = new ArrayList<String>(Arrays.asList("copy", "paste", "delete"));
		
		String[] labelsArray = //labelsList.toArray(new String[0]); 		// old way
							   labelsList.stream().toArray(String[]::new);
		
		System.out.println(Arrays.toString(labelsArray));
		assertThat(labelsArray, is(arrayContainingInAnyOrder(labelsList.toArray())));
	}

    @Test
    public void test_factory_pattern() {
        // When
        Component button = ComponentFactory.createComponent(BUTTON, "Click!");

        // Then
        assertThat(button, instanceOf(Button.class));
        assertThat(((Button) button).getLabel(), is("Click!"));
    }
}
