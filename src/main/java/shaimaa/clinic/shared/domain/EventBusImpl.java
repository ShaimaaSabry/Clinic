package shaimaa.clinic.shared.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventBusImpl {
    private Logger logger = LoggerFactory.getLogger(EventBusImpl.class.getName());
    private final List<EventListener<?>> listeners = new ArrayList<>();

    public <T> void subscribe(Class<T> eventType, EventBus.EventCallback<T> callback) {
        logger.debug(
                "Subscribing to event: {} with callback: {}",
                eventType.getSimpleName(),
                callback.getClass().getSimpleName()
        );
        EventListener<T> listener = new EventListener<>(eventType, callback);
        this.listeners.add(listener);
    }

    public <T> void push(T event) {
        logger.debug(
                "Pushing event: {}",
                event.getClass().getSimpleName()
        );
        for (EventListener<?> listener : listeners) {
            if (listener.eventType().isInstance(event)) {
                logger.debug(
                        "Pushing event: {} to listener {}",
                        event.getClass().getSimpleName(),
                        listener.callback().getClass().getSimpleName()
                );
                @SuppressWarnings("unchecked")
                EventListener<T> typedListener = (EventListener<T>) listener;
                typedListener.callback().handle(event);
            }
        }
    }
}

record EventListener<T>(
        Class<T> eventType,
        EventBus.EventCallback<T> callback
) { }
