package shaimaa.clinic.slots.internal.domain.events;

import shaimaa.clinic.slots.internal.repositories.SlotEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public record SlotCreated(
        UUID id,
        LocalDateTime startsAt,
        Float cost
) {
    public static SlotCreated from(
            SlotEntity slot
    ) {
        return new SlotCreated(
                slot.getId(),
                slot.getStartsAt(),
                slot.getCost()
        );
    }
}
