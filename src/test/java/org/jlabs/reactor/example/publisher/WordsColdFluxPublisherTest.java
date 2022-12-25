package org.jlabs.reactor.example.publisher;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

public class WordsColdFluxPublisherTest {

    @Test
    void shouldReturnFilteredWords() {
        final List<String> words = List.of("orange", "apple", "plum", "peach", "grape", "pineapple");
        StepVerifier.create(WordsColdFluxPublisher.toFluxStreamWithFilter(words, 5))
                .expectNext(Pair.of("apple", 5))
                .expectNext(Pair.of("plum", 4))
                .expectNext(Pair.of("peach", 5))
                .expectNext(Pair.of("grape", 5))
                .expectComplete()
                .verify();
    }
}
