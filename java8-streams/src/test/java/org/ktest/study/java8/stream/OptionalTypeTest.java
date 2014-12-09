package org.ktest.study.java8.stream;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.ktest.study.java8.datatype.MyMath;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class OptionalTypeTest {
    
    @Rule
    public ExpectedException thrown =  ExpectedException.none();
    
    @Test
    public void creatingOptionalType() {
        Integer i = 1;
        Optional<Integer> value = Optional.of(i);
        System.out.println("value is " + value);
        
        // value = Optional.of(null); // *** Exception ***
        // Cannot create Optional type with null value.
        // Use Option.ofNullable()
        
        value = Optional.ofNullable(i);
        System.out.println("Optional.ofNullable(1): value is " + value);
        
        i = null;
        value = Optional.ofNullable(null);
        System.out.println("Optional.ofNullable(null): value is " + value);

        value = Optional.empty();
        System.out.println("Optional.empty(): value is " + value);
    }
    
    @Test
    public void isPresent() {
        Optional<Integer> value = Optional.of(1);
        
        if (value.isPresent()){
            System.out.println("Value is present" + value.get());
            assertThat(value.get(), is(1));
        }
        
        // check again with null value
        Optional<Integer> value2 = Optional.empty();       
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage("No value present");
        value2.get();
        
        // same as 
        // if (value != null) { doSomething(value); }
        // not very useful. See other tests for right usage.
    }
    
    @Test
    public void processValueIfPresent() {
        Optional<Integer> value1 = Optional.of(1);
        
        value1.ifPresent(x -> {
            System.out.println("value1 is present : " + x);
            assertThat(x, is(1));   
        }); 
        
    }
    
    @Test
    public void mapValueIfPresent() {
        Optional<Integer> value1 = Optional.of(1);
        
        Optional<Double> result = value1.map(x -> x * 2.0);
        
        result.ifPresent(x -> {
            System.out.println("result is " + x);
            assertThat(x, is(2.0));   
        });
    }
    
    @Test
    public void returnDefaultValueIfNotPresent() {
        Optional<Integer> value1 = Optional.empty();
        
        // return a default value
        assertThat(value1.orElse(10), is(10));
        
        // return value produced by a generator function
        assertThat(value1.orElseGet(() -> new Random().nextInt()), notNullValue());
        assertThat(value1.orElseGet(() -> 10), is(10));
        
        thrown.expect(IllegalStateException.class);
        value1.orElseThrow(IllegalStateException::new);
    }
    
    @Test
    public void flatMap() {
        
        Optional<Double> result = Optional.of(4.0)
                                          .flatMap(MyMath::inverse)
                                          .flatMap(MyMath::sqrt);
        
        // similar to something like Double.inverse().sqrt()
        // but flatMap works with Optional type.
        
        result.ifPresent(x -> assertThat(x, is(0.5)));
    }

}
