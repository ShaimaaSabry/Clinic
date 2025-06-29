package shaimaa.clinic.appointments.shared;

import shaimaa.clinic.appointments.internal.core.services.AppointmentService;

public class AppointmentsFacadeImpl implements AppointmentsFacade{
    private final AppointmentService appointmentService;

    AppointmentsFacadeImpl(final AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public void createAppointment(CreateAppointmentRequest request) {
//        this.appointmentService.createAppointment(request.slotId(), request.patientId());
    }
}
