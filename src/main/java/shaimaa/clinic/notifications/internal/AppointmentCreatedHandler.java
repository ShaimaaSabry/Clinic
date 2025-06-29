package shaimaa.clinic.notifications.internal;

import org.springframework.stereotype.Component;
import shaimaa.clinic.appointments.shared.dtos.AppointmentCreatedDto;
import shaimaa.clinic.booking.internal.application.commands.CreateSlot.CreateSlotCommand;
import shaimaa.clinic.booking.internal.application.commands.CreateSlot.CreateSlotHandler;
import shaimaa.clinic.booking.shared.dtos.SlotBookedDto;
import shaimaa.clinic.shared.domain.EventBusImpl;

@Component
public class AppointmentCreatedHandler {
    private final EventBusImpl eventBus;
    private final NotificationsService notificationsService;

    AppointmentCreatedHandler(
            EventBusImpl eventBus,
            NotificationsService notificationsService
    ) {
        this.eventBus = eventBus;
        this.eventBus.subscribe(
                AppointmentCreatedDto.class,
                this::handle
        );
        this.notificationsService = notificationsService;
    }

    void handle(AppointmentCreatedDto dto) {
        this.notificationsService.sendNotificationToPatient(
                dto.slotStartsAt()
        );

        this.notificationsService.sendNotificationToDoctor(
                dto.slotStartsAt(),
                dto.patientFirstName(),
                dto.patientLastName()
        );
    }
}
