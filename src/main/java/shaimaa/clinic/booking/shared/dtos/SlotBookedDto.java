package shaimaa.clinic.booking.shared.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record SlotBookedDto(
        UUID slotId,
        LocalDateTime slotStartsAt,
        float slotCost,
        UUID patientId
) {
}
