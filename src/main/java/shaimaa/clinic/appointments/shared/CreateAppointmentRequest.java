package shaimaa.clinic.appointments.shared;

import shaimaa.clinic.appointments.internal.shell.repository.AppointmentEntity;

import java.util.UUID;

public record CreateAppointmentRequest(
        UUID slotId,
        UUID patientId
) {

    AppointmentEntity toEntity() {
        return null;
    }
}
