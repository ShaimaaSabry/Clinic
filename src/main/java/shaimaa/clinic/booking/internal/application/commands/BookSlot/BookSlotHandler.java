package shaimaa.clinic.booking.internal.application.commands.BookSlot;

import org.springframework.stereotype.Component;
import shaimaa.clinic.booking.internal.domain.contracts.BookingPublisher;
import shaimaa.clinic.booking.internal.domain.contracts.SlotRepository;
import shaimaa.clinic.booking.internal.domain.model.Slot;

@Component
public class BookSlotHandler {
    private final SlotRepository slotRepository;
    private final BookingPublisher bookingPublisher;

    BookSlotHandler(
            final SlotRepository slotRepository,
            final BookingPublisher bookingPublisher
    ) {
        this.slotRepository = slotRepository;
        this.bookingPublisher = bookingPublisher;
    }

    public void handle(BookSlotCommand command) {
        Slot slot = this.slotRepository.getSlotById(command.slotId()).get();

        this.slotRepository.deleteSlot(command.slotId());

        this.bookingPublisher.publish(slot, command.patientId());
    }
}
