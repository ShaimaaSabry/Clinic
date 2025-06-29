package shaimaa.clinic.appointments.internal.shell.api.v1.GetAppointments;

import shaimaa.clinic.appointments.internal.core.model.Patient;

import java.util.UUID;

record PatientDto(
        UUID id,
        String name
) {
    static PatientDto of(Patient patient) {
        if (patient == null) return null;

        return new PatientDto(
                patient.getId(),
                "%s %s".formatted(patient.getFirstName(), patient.getLastName())
        );
    }
}
