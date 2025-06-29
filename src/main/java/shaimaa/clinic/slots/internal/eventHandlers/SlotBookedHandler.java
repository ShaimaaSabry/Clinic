package shaimaa.clinic.slots.internal.eventHandlers;

import org.springframework.stereotype.Component;
import shaimaa.clinic.appointments.internal.core.services.AppointmentService;
import shaimaa.clinic.booking.shared.dtos.SlotBookedDto;
import shaimaa.clinic.shared.domain.EventBusImpl;
import shaimaa.clinic.slots.internal.domain.service.SlotService;

@Component("slotsSlotBookedHandler")
public class SlotBookedHandler {
    private final EventBusImpl eventBus;
    private final SlotService slotService;

    public SlotBookedHandler(
            EventBusImpl eventBus,
            SlotService appointmentService
    ) {
        this.eventBus = eventBus;
        this.eventBus.subscribe(
                SlotBookedDto.class,
                this::handle
        );
        this.slotService = appointmentService;
    }

    void handle(SlotBookedDto dto) {
        this.slotService.reserveSlot(
                dto.slotId()
        );
    }
}
