package shaimaa.clinic.appointments.internal.shell.api.v1.GetAppointments;

import shaimaa.clinic.appointments.internal.core.model.Appointment;

import java.util.List;

public record GetAppointmentsResponse(
        List<AppointmentDto> appointments
) {
    public static GetAppointmentsResponse of(List<Appointment> appointments) {
        return new GetAppointmentsResponse(
                appointments.stream()
                        .map(AppointmentDto::of)
                        .toList()
        );
    }
}
