package org.jlabs.reactor.example.publisher;

import org.apache.commons.lang3.tuple.Pair;
import reactor.core.publisher.Flux;

import java.util.List;

public class WordsColdFluxPublisher {

    public static Flux<Pair<String, Integer>> toFluxStreamWithFilter(final List<String> words, final int maxWordLength) {
        return Flux.fromIterable(words)
                .filter(word -> word.length() <= maxWordLength)
                .map(element -> Pair.of(element, element.length()));
    }
}
