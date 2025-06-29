package shaimaa.clinic.appointments.internal.core.contracts;

import shaimaa.clinic.appointments.internal.core.model.Appointment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository {
    List<Appointment> getUpcomingAppointments();
    Optional<Appointment> getAppointment(UUID appointmentId);
    void save(Appointment appointment);
}
