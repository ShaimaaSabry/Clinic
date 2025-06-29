package shaimaa.clinic.booking.internal.application.commands.CreateSlot;

import org.springframework.stereotype.Component;
import shaimaa.clinic.booking.internal.domain.contracts.SlotRepository;
import shaimaa.clinic.booking.internal.domain.model.Slot;

@Component
public class CreateSlotHandler {
    private SlotRepository slotRepository;

    public CreateSlotHandler(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public void handle(CreateSlotCommand command) {
        Slot slot = Slot.newSlot(
                command.id(),
                command.startsAt(),
                command.cost()
        );

        this.slotRepository.save(slot);
    }
}
