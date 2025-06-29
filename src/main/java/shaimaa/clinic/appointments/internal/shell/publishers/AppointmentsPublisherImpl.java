package shaimaa.clinic.appointments.internal.shell.publishers;

import org.springframework.stereotype.Component;
import shaimaa.clinic.appointments.internal.core.contracts.AppointmentsPublisher;
import shaimaa.clinic.appointments.internal.core.model.Appointment;
import shaimaa.clinic.appointments.shared.dtos.AppointmentCreatedDto;
import shaimaa.clinic.shared.domain.EventBusImpl;

@Component
public class AppointmentsPublisherImpl implements AppointmentsPublisher {
    private final EventBusImpl eventBus;

    AppointmentsPublisherImpl(EventBusImpl eventBus) {
        this.eventBus = eventBus;
    }

    public void publish(Appointment appointment) {
        this.eventBus.push(
                new AppointmentCreatedDto(
                        appointment.getId(),
                        appointment.getSlotStartsAt(),
                        appointment.getSlotCost(),
                        appointment.getPatient().getId(),
                        appointment.getPatient().getFirstName(),
                        appointment.getPatient().getLastName()
                )
        );
    }
}
