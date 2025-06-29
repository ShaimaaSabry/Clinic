package shaimaa.clinic.appointments.internal.shell.eventHandlers;

import org.springframework.stereotype.Component;
import shaimaa.clinic.appointments.internal.core.services.AppointmentService;
import shaimaa.clinic.booking.shared.dtos.SlotBookedDto;
import shaimaa.clinic.shared.domain.EventBusImpl;

@Component("appointmentsSlotBookedHandler")
public class SlotBookedHandler {
    private final EventBusImpl eventBus;
    private final AppointmentService appointmentService;

    public SlotBookedHandler(
            EventBusImpl eventBus,
            AppointmentService appointmentService
    ) {
        this.eventBus = eventBus;
        this.eventBus.subscribe(
                SlotBookedDto.class,
                this::handle
        );
        this.appointmentService = appointmentService;
    }

    void handle(SlotBookedDto dto) {
        this.appointmentService.createAppointment(
                dto.slotId(),
                dto.slotStartsAt(),
                dto.slotCost(),
                dto.patientId()
        );
    }
}
