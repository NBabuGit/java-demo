package com.demo.streams;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertThat(count).isEqualTo(4);
    }

    @Test
    void given_stream_find_any_element_starting_with() {
        Stream.of("Raj", "Ram", "Leka", "Hari")
                .filter(name -> name.startsWith("L"))
                .findAny()
                .ifPresent(System.out::println);
    }

    @Test
    void given_stream_add_new_stream_at_the_beginning() {
        Stream<Integer> stream1 = Stream.of(3, 5, 7,9);
        Stream<Integer> stream2 = Stream.of(2);
        Stream<Integer>  finalStream = Stream.concat(stream2, stream1);
        assertThat(finalStream.findFirst().get()).isEqualTo(2);
    }

    @Test
    void split_iterator_usage() {
        Stream<Integer> stream1 = Stream.of(3, 5, 7,9);
        Spliterator<Integer> spliterator= stream1.spliterator();
        Iterator<Integer> iterator = Spliterators.iterator(spliterator);
        Stream<Integer> finalStream = Stream.concat(Stream.concat(
                Stream.generate(iterator::next).limit(2),
                Stream.of(6)), StreamSupport.stream(spliterator,false));
        finalStream.forEach(System.out::println);
    }

    @Test
    void stream_consumed_reuse_throws_IllegalStateException() {
        Stream<Integer> stream = Stream.of(4,5,7,9,1,3);
        List<Integer> evenList = stream
                .filter(x -> x%2==0)
                .collect(toList());
        System.out.println("evenList:"+evenList);
        assertThatThrownBy(() -> stream.filter(x -> x%2!=0)
                .collect(toList())).isInstanceOf(IllegalStateException.class)
                        .hasMessageContaining("stream has already been operated upon or closed");
    }

    @Test
    void stream_consumed_reuse_without_IllegalStateException() {
        Supplier<Stream<Integer>> supplierStream = () -> Stream.of(4,5,7,9,1,3);
        List<Integer> evenList = supplierStream.get()
                .filter(x -> x%2==0)
                .collect(toList());
        System.out.println("evenList:"+evenList);
        List<Integer> oddList = supplierStream.get()
                .filter(x -> x%2!=0)
                .collect(toList());
        System.out.println("oddList:"+oddList);
    }

    @Test
    void find_odd_even_values() {
        List<Integer> list = Arrays.asList(4, 5, 7, 9, 1, 3);
        List<Integer> evenList = list.stream()
                .filter(x -> x%2==0)
                .collect(toList());
        System.out.println("evenList:"+evenList);
        List<Integer> oddList  =list.stream().filter(x -> x%2!=0)
                .collect(toList());
        System.out.println("oddList:"+oddList);
    }

    @Test
    @DisplayName("streams for each break simulation..added in Java9")
    void streams_for_each_break_scenario() {
        Stream<String> names = Stream.of("abc", "def", "aklk", "abcdef", "kfg");
        names.takeWhile(name -> (name.length()%2) !=0)
                .forEach(System.out::println);
    }
}
