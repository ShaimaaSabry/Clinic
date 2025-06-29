package shaimaa.clinic.appointments.internal.core.contracts;

import shaimaa.clinic.appointments.internal.core.model.Appointment;

public interface AppointmentsPublisher {
    void publish(Appointment appointment);
}
