package pl.vanta.adventofcode;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class Gatherers {

    public static <A, B, R> Gatherer<A, ?, R> cartesian(
            Stream<B> bs,
            BiFunction<A, B, R> combiner) {

        List<B> buffer = bs.toList();   // must buffer B once

        return Gatherer.of(
                // 1. initializer
                Object::new,

                // 2. accumulator
                (state, elementA, downstream) -> {
                    for (B b : buffer) {
                        if (!downstream.push(combiner.apply(elementA, b))) {
                            return false;  // stop early if downstream requests stop
                        }
                    }
                    return true;
                },

                // 3. combiner (unused, Gatherer is not parallel-friendly)
                (left, right) -> left,

                // 4. finisher (nothing to emit)
                (state, downstream) -> {
                }
        );
    }
}
