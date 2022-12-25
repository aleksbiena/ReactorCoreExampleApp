package org.jlabs.reactor.example;

import org.apache.commons.lang3.tuple.Pair;
import org.jlabs.reactor.example.publisher.FluxHotPublisher;
import org.jlabs.reactor.example.publisher.WordsColdFluxPublisher;
import org.jlabs.reactor.example.subscriber.SubscriberWithCustomBackpressure;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class ReactorExamples {

    public void performFluxColdPublisherExample(final List<String> elements) {
        final Flux<Pair<String, Integer>> wordsFluxStream = WordsColdFluxPublisher.toFluxStreamWithFilter(elements, 5);
        wordsFluxStream
                .log()
                .subscribe();
    }

    public void performMonoColdPublisherExample(final List<String> elements) {
        final Mono<List<Pair<String, Integer>>> wordListMono = WordsColdFluxPublisher.toFluxStreamWithFilter(elements, 5)
                .collectList();
        wordListMono
                .doOnEach(System.out::println)
                .subscribe();
    }

    public void performHotPublisherExample(final List<String> elements) throws InterruptedException {
        final Flux<String> hotWordsStream = FluxHotPublisher.toHotFluxStream(Flux.fromIterable(elements));

        hotWordsStream
                .doOnSubscribe(s -> System.out.println("Subscriber 1 subscribed to source"))
                .subscribe(element -> System.out.println("Subscriber 1 receiving: " + element), e -> {}, () -> {});
        //Subscriber 2 joins after sometime
        Thread.sleep(3500);
        hotWordsStream
                .doOnSubscribe(s -> System.out.println("Subscriber 2 subscribed to source"))
                .subscribe(element -> System.out.println("Subscriber 2 receiving: " + element), e -> {}, () -> {});
        Thread.sleep(7000);
    }

    public void performHotPublisherConnectableFluxExample(final List<String> elements) throws InterruptedException {
        final ConnectableFlux<String> connectableFlux = FluxHotPublisher.toConnectableFlux(Flux.fromIterable(elements));

        connectableFlux
                .doOnSubscribe(s -> System.out.println("Subscriber 1 subscribed to source"))
                .subscribe(element -> System.out.println("Subscriber 1 receiving: " + element), e -> {}, () -> {});
        //Subscriber 2 joins after sometime
        Thread.sleep(3500);
        connectableFlux
                .doOnSubscribe(s -> System.out.println("Subscriber 2 subscribed to source"))
                .subscribe(element -> System.out.println("Subscriber 2 receiving: " + element), e -> {}, () -> {});
        Thread.sleep(7000);

        connectableFlux.connect();
    }

    public void performCustomBackpressureExample(final List<String> elements) {
        Flux.fromIterable(elements)
                .log()
                .subscribe(new SubscriberWithCustomBackpressure());
    }
}
