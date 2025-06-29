package shaimaa.clinic.booking.internal.application.commands.CreateSlot;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateSlotCommand(
        UUID id,
        LocalDateTime startsAt,
        Float cost
) {
}
