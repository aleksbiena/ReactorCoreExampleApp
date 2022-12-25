package org.jlabs.reactor.example;

import java.util.List;

public class ReactorWordsAppExample {

    public static void main(String[] args) throws InterruptedException {
        final List<String> words = List.of("orange", "apple", "plum", "peach", "grape", "pineapple");

        final ReactorExamples reactorExamples = new ReactorExamples();
        reactorExamples.performFluxColdPublisherExample(words);
        reactorExamples.performHotPublisherExample(words);
        reactorExamples.performCustomBackpressureExample(words);
    }
}
