package shaimaa.clinic.appointments.shared;

import java.time.LocalDateTime;

public record AppointmentCreated(
    LocalDateTime startsAt,
    String patientName,
    String doctorName,

    String patientMobileToken,
    String doctorMobileToken
) {

}
