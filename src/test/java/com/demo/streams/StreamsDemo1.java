package com.demo.streams;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class StreamsDemo1 {

    @Test
    void given_stream_print_all_elements(){
        Stream.of("Raj", "Ram", "Leka", "Hari")
                .forEach(System.out::println);
    }

    @Test
    void given_stream_count_number_of_elements() {
        Long count = Stream.of("Raj", "Ram", "Leka", "Hari")
                .count();
        Assertions.assertThat(count).isEqualTo(4);
    }

    @Test
    void given_stream_find_any_element_starting_with() {
        Stream.of("Raj", "Ram", "Leka", "Hari")
                .filter(name -> name.startsWith("L"))
                .findAny()
                .ifPresent(System.out::println);
    }
}
