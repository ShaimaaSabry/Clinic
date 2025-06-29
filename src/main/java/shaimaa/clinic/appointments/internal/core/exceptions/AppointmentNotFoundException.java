package shaimaa.clinic.appointments.internal.core.exceptions;

import java.util.UUID;

public class AppointmentNotFoundException extends Exception {
    private AppointmentNotFoundException(String message) {
        super(message);
    }

    public static AppointmentNotFoundException notFound(UUID appointmentId) {
        return new AppointmentNotFoundException("Invalid appointment id %s".formatted(appointmentId));
    }
}
