package shaimaa.clinic.appointments.shared.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentCreatedDto(
        UUID slotId,
        LocalDateTime slotStartsAt,
        float slotCost,
        UUID patientId,
        String patientFirstName,
        String patientLastName
) {
}
