package shaimaa.clinic.shared.domain;

public interface EventBus {
    @FunctionalInterface
    interface EventCallback<T> {
        void handle(T event);
    }
}
