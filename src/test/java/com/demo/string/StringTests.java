package com.demo.string;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class StringTests {

    @Test
    @DisplayName("reference_equality_of_two_strings")
    void reference_equality_of_two_strings() {
        String s1 = "Hello";
        String s2 = "Hello";
        Assertions.assertThat(s1)
                        .as("Both objects reference same value")
                        .isSameAs(s2);
    }

    @Test
    @DisplayName("content_equality_of_two_strings")
    void content_equality_of_two_strings() {
        String s2 = "Hello";
        String s3 = new String("Hello");
        Assertions.assertThat(s2)
                .as("Both String have same content")
                .isEqualTo(s3);
        Assertions.assertThat(s2)
                .isNotSameAs(s3);
    }

    @Test
    @DisplayName("reference equality comparison of String created with literal and valueOf")
    void reference_equality_strings_1() {
        String s1 = "Hello";
        String s2 = String.valueOf("Hello");
        Assertions.assertThat(s1)
                .isSameAs(s2);
        Assertions.assertThat(s1.hashCode()).isEqualTo(s2.hashCode());

    }

    @Test
    @DisplayName("String reference equality of created by literal and String object intern ")
    void string_reference_equality_scenario_3() {
        String s1 = "Hello";
        String s2 = new String("Hello").intern();
        Assertions.assertThat(s1).isSameAs(s2);
    }

    @Test
    @DisplayName("String content equality of created by literal and String object  ")
    void string_reference_equality_scenario_4() {
        String s1 = "Hello";
        String s2 = new String("Hello");
        Assertions.assertThat(s1).isEqualTo(s2);
        Assertions.assertThat(s1).isNotSameAs(s2);
    }

}
