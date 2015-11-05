package org.ktest.java8.study.lambdas;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Manu on 13-Dec-14.
 */
public class OptionalTest {

    @Test
    public void test_isPresent() {
        Optional<String> text = Optional.ofNullable(null);
        if (text.isPresent()) {
            System.out.println("There is value...");
        } else {
            System.out.println("Optional is empty...");
        }
    }

    @Test
    public void test_orElseGet() {
        // Given
        Optional<String> text = Optional.ofNullable(null);

        // When
        String value = text.orElseGet(() -> "Hello");

        // Then
        assertThat(value, is("Hello"));
    }

    @Test
    public void test_orElse() {
        // Given
        Optional<String> text = Optional.ofNullable(null);

        // When
        String value = text.orElse("Hello");

        // Then
        assertThat(value, is("Hello"));
    }

    @Test
    public void test_ifPresent() {
        Optional<String> text = Optional.of("Hello");
        text.ifPresent(value -> System.out.println("Optional is not empty. And the value is " + value));
    }

    @Test
    public void test_filter() {
        // Given
        Optional<String> text = Optional.of("Hello");

        // When
        String value = text.filter(x -> x.length() > 6).orElse("Hello World");

        // Then
        assertThat(value, is("Hello World"));
    }

    @Test
    public void test_map() {
        // Given
        Optional<String> text = Optional.of("Hello");

        // When
        String value = text.map(String::toUpperCase).get();

        // Then
        assertThat(value, is("HELLO"));
    }

    @Test
    public void test_map_to_another_type() {
        // Given
        Optional<String> text = Optional.ofNullable("Hello");

        // When
        text.map(String::length).ifPresent(System.out::println);

        // Just to re-assert that map is stateless.
        assertThat(text.get(), is("Hello"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_orElseThrow() {
        // Given
        Optional<String> text = Optional.ofNullable(null);

        // When
        text.orElseThrow(() -> new IllegalArgumentException());

        // Then the exception is thrown.
    }
}
