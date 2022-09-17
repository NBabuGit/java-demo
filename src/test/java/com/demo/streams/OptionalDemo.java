package com.demo.streams;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class OptionalDemo {

    @Test
    void given_value_exists_print() {
        Optional<String> stringOptional = Optional.of("Hello");
        stringOptional.ifPresent(System.out::println);
    }

    @Test
    void given_value_not_exists_no_print() {
        Optional<String> stringOptional = Optional.empty();
        stringOptional.ifPresent(System.out::println);
    }

    @Test
    void given_value_null_throw_exception() {
        Optional<String> stringOptional = null;
        assertThatThrownBy(() -> stringOptional.ifPresent(System.out::println))
                .isInstanceOf(NullPointerException.class);

    }

    @Test
    void given_value_null_else() {
        Optional<String> stringOptional = Optional.empty();
         stringOptional.ifPresentOrElse(System.out::println,
                () -> System.out.println("No value exist"));
    }

    @Test
    void optional_equality() {
        Optional<String> obj1 = Optional.of("Hello");
        Optional<String> obj2 = Optional.of("Hello");
        assertThat(obj1).isEqualTo(obj2)
                .hasValue("Hello");
        assertThat(obj1).isNotSameAs(obj2);
    }

    @Test
    void optional_equality_scenario_2() {
        String s = "Hello";
        Optional<String> obj1 = Optional.of(s);
        Optional<String> obj2 = Optional.of(s);
        System.out.println("obj1 hashCode:"+ Objects.hashCode(obj1));
        System.out.println("obj2 hashCode:"+obj2.hashCode());
        System.out.println("s hashCode:"+s.hashCode());
        System.out.println(obj1==obj2);
        assertThat(obj1).isEqualTo(obj2)
                .hasValue("Hello");
        assertThat(obj1).isNotSameAs(obj2);
    }

    @Test
    @DisplayName("Optional or usage demo")
    void optional_or_usage_demo() {
        Optional<String> optionalStr1 = Optional.empty();
        Optional<String> optional1 = Optional.of("Hello");
        assertThat(optionalStr1.or(() ->optional1).get()).isEqualTo("Hello")
                .hasToString("Hello");
    }

    @Test
    @DisplayName("Optional or usage demo- retrieve value from first Optional")
    void optional_or_usage_demo_2() {
        Optional<String> optional1 = Optional.of("Hello");
        Optional<String> optionalStr1 = Optional.empty();
        assertThat(optional1.or(() ->optionalStr1).get()).isEqualTo("Hello")
                .hasToString("Hello");
    }

    @Test
    @DisplayName("Optional with filter predicate usage")
    void optional_value_matching_with() {
        Optional<String> optionalName = Optional.of("Ram");
        String name = optionalName.filter(name1 -> name1.startsWith("R"))
                .get();
        assertThat(name).isEqualTo("Ram");
    }

    @Test
    @DisplayName("Optional with filter predicate usage 2")
    void optional_filter_not_matched_with_condition() {
        Optional<String> optName = Optional.of("Ram");
        assertThatThrownBy(() -> optName.filter(x ->  x.contains("Kiran")).get())
                .isInstanceOf(NoSuchElementException.class);
    }


}
