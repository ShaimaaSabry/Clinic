package shaimaa.clinic.appointments.internal.shell.api.v1.GetAppointments;

import com.fasterxml.jackson.annotation.JsonFormat;
import shaimaa.clinic.appointments.internal.core.model.Appointment;

import java.time.LocalDateTime;
import java.util.UUID;

record AppointmentDto(
    UUID id,

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime startsAt,

    float cost,

    PatientDto patient,

    String status

) {
    static AppointmentDto of(Appointment appointment) {
        return new AppointmentDto(
            appointment.getId(),
            appointment.getSlotStartsAt(),
            appointment.getSlotCost(),
            PatientDto.of(appointment.getPatient()),
                appointment.getStatus().toString()
        );
    }
}
