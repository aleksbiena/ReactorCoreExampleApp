package org.jlabs.reactor.example.publisher;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxHotPublisher {
    public static Flux<String> toHotFluxStream(final Flux<String> fluxStream) {
        return fluxStream
                .delayElements(Duration.ofMillis(1000))
                .share();
    }

    public static ConnectableFlux<String> toConnectableFlux(final Flux<String> fluxStream) {
        return fluxStream.publish();
    }
}
