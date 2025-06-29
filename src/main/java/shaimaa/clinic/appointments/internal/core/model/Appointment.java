package shaimaa.clinic.appointments.internal.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@ToString
public class Appointment {
    private UUID id;

    private UUID slotId;

    private LocalDateTime slotStartsAt;

    private float slotCost;

    private Patient patient;

    private AppointmentStatus status;

    public static Appointment newAppointment(UUID slotId, UUID patientId) {
        return new Appointment(
                null,
                slotId,
                null,
                5,
                null,
                AppointmentStatus.SCHEDULED
        );
    }

    public static Appointment from(
            UUID id,
            UUID slotId,
            LocalDateTime slotStartsAt,
            float slotCost,
            Patient patient,
            AppointmentStatus status
    ) {
        return new Appointment(
                id,
                slotId,
                slotStartsAt,
                slotCost,
                patient,
                status
        );
    }

    public void complete() {
        this.status = AppointmentStatus.COMPLETED;
    }

    public void cancel() {
        this.status = AppointmentStatus.CANCELLED;
    }
}
