package org.jlabs.reactor.example.subscriber;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

public class SubscriberWithCustomBackpressure extends BaseSubscriber<String> {

    private static int elementsReceived = 0;
    private static final int BACKPRESSURE_MAX_BATCH_SIZE = 2;

    @Override
    public void hookOnSubscribe(Subscription subscription) {
        request(BACKPRESSURE_MAX_BATCH_SIZE);
        ++elementsReceived;
    }

    @Override
    public void hookOnNext(String element) {
        if (elementsReceived % BACKPRESSURE_MAX_BATCH_SIZE == 1) {
            request(BACKPRESSURE_MAX_BATCH_SIZE);
        }
        ++elementsReceived;
    }
}
