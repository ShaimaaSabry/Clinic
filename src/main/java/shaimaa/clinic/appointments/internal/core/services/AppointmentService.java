package shaimaa.clinic.appointments.internal.core.services;

import org.springframework.stereotype.Service;
import shaimaa.clinic.appointments.internal.core.contracts.AppointmentRepository;
import shaimaa.clinic.appointments.internal.core.exceptions.AppointmentNotFoundException;
import shaimaa.clinic.appointments.internal.core.model.Appointment;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    AppointmentService(final AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getUpcomingAppointments() {
        return this.appointmentRepository.getUpcomingAppointments();
    }

//    public void createAppointment(UUID slotId, UUID patientId) {
//        Appointment appointment = Appointment.newAppointment(slotId, patientId);
//
//        this.appointmentRepository.createAppointment(appointment);
//    }

    public void cancelAppointment(UUID appointmentId) throws AppointmentNotFoundException {
        Appointment appointment = this.appointmentRepository.getAppointment(appointmentId)
                .orElseThrow( () ->
                    AppointmentNotFoundException.notFound(appointmentId)
                );

        appointment.cancel();

        this.appointmentRepository.updateAppointment(appointment);
    }

    public void completeAppointment(UUID appointmentId) throws AppointmentNotFoundException {
        Appointment appointment = this.appointmentRepository.getAppointment(appointmentId)
                .orElseThrow( () ->
                        AppointmentNotFoundException.notFound(appointmentId)
                );

        appointment.complete();

        this.appointmentRepository.updateAppointment(appointment);
    }
}
