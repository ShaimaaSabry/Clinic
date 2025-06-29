package shaimaa.clinic.booking.internal.application.commands.BookSlot;

import java.util.UUID;

public record BookSlotCommand(
        UUID slotId,
        UUID patientId
) {
}
