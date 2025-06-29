package shaimaa.clinic.booking.internal.application.commands.BookSlot;

//import shaimaa.clinic.booking.internal.domain.contracts.AppointmentsGateway;

import org.springframework.stereotype.Component;
import shaimaa.clinic.booking.internal.domain.contracts.SlotRepository;

@Component
public class BookSlotHandler {
    private final SlotRepository slotRepository;
//    private final AppointmentsGateway appointmentsGateway;

    BookSlotHandler(
            final SlotRepository slotRepository
//            final AppointmentsGateway appointmentsGateway
    ) {
        this.slotRepository = slotRepository;
//        this.appointmentsGateway = appointmentsGateway;
    }

    public void handle(BookSlotCommand command) {
        this.slotRepository.deleteSlot(command.slotId());
//        Appointment appointment = Appointment.newAppointment(command.slotId(), command.patientId());

//        this.appointmentsGateway.createAppointment(appointment);
    }
}
