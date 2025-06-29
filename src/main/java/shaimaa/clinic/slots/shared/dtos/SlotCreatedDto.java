package shaimaa.clinic.slots.shared.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record SlotCreatedDto(
        UUID id,
        LocalDateTime startsAt,
        Float cost
) {
}
