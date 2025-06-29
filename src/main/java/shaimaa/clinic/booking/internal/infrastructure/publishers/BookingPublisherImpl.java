package shaimaa.clinic.booking.internal.infrastructure.publishers;

import org.springframework.stereotype.Component;
import shaimaa.clinic.booking.internal.domain.contracts.BookingPublisher;
import shaimaa.clinic.booking.internal.domain.model.Slot;
import shaimaa.clinic.booking.shared.dtos.SlotBookedDto;
import shaimaa.clinic.shared.domain.EventBusImpl;

import java.util.UUID;

@Component
public class BookingPublisherImpl implements BookingPublisher {
    private final EventBusImpl eventBus;

    BookingPublisherImpl(EventBusImpl eventBus) {
        this.eventBus = eventBus;
    }

    public void publish(Slot slot, UUID patientId) {
        this.eventBus.push(
                new SlotBookedDto(
                        slot.getId(),
                        slot.getStartsAt(),
                        slot.getCost(),
                        patientId
                )
        );
    }
}
