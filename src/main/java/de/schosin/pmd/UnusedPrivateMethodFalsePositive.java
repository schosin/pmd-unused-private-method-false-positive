package de.schosin.pmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;

import de.schosin.pmd.UnusedPrivateMethodFalsePositive.Data;

public class UnusedPrivateMethodFalsePositive {

    public static void main(String[] args) {
        var instance = new UnusedPrivateMethodFalsePositive();
        instance.doWork();
    }

    private List<Result> doWork() {
        // lookup map of parent -> children
        var lookup = System.currentTimeMillis() > 100L // permission based
                ? Dao.source1().collect(groupingByNullable(Data::parent)) // DAO call #1
                : Dao.source2().collect(groupingByNullable(Data::parent)); // DAO call #2

        // start with root elements (null parent)
        return lookup.getOrDefault(null, List.of()).stream()
                .map(key -> falsePositive(key, lookup))
                .toList();
    }

    private Result falsePositive(Data data, Map<String, List<Data>> lookup) {
        // recursive call
        var children = lookup.getOrDefault(data.id, List.of()).stream()
                .map(child -> falsePositive(child, lookup))
                .toList();

        return new Result(data.id, children);
    }

    record Data(String id, String parent) {
    }

    record Result(String id, List<Result> children) {
    }

    static <T, A> Collector<T, ?, Map<A, List<T>>> groupingByNullable(Function<? super T, ? extends A> classifier) {
        return java.util.stream.Collectors.toMap(
                classifier,
                Collections::singletonList,
                (list, item) -> {
                    if (!(list instanceof ArrayList<T>)) {
                        list = new ArrayList<>(list);
                    }

                    list.addAll(item);
                    return list;
                });
    }

}

class Dao {

    static Stream<Data> source1() {
        return Stream.of(new Data("foo", "bar"), new Data("foo2", "bar"), new Data("bar", null), new Data("hello", null));
    }

    static Stream<Data> source2() {
        return Stream.of(new Data("foo", "bar"), new Data("bar", null), new Data("hello", null));
    }

}