package shaimaa.clinic.appointments.internal.core.services;

import org.springframework.stereotype.Service;
import shaimaa.clinic.appointments.internal.core.contracts.AppointmentRepository;
import shaimaa.clinic.appointments.internal.core.contracts.AppointmentsPublisher;
import shaimaa.clinic.appointments.internal.core.contracts.PatientRepository;
import shaimaa.clinic.appointments.internal.core.exceptions.AppointmentNotFoundException;
import shaimaa.clinic.appointments.internal.core.model.Appointment;
import shaimaa.clinic.appointments.internal.core.model.Patient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final AppointmentsPublisher appointmentsPublisher;

    AppointmentService(
            final AppointmentRepository appointmentRepository,
            final PatientRepository patientRepository,
            final AppointmentsPublisher appointmentsPublisher
    ) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.appointmentsPublisher = appointmentsPublisher;
    }

    public List<Appointment> getUpcomingAppointments() {
        return this.appointmentRepository.getUpcomingAppointments();
    }

    public void createAppointment(
            UUID slotId,
            LocalDateTime slotStartsAt,
            float slotCost,
            UUID patientId
    ) {
        Patient patient = this.patientRepository.getPatientById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with ID: " + patientId));

        Appointment appointment = Appointment.newAppointment(
                slotId,
                slotStartsAt,
                slotCost,
                patientId,
                patient.getFirstName(),
                patient.getLastName()
        );

        this.appointmentRepository.save(appointment);

        this.appointmentsPublisher.publish(appointment);
    }

    public void cancelAppointment(UUID appointmentId) throws AppointmentNotFoundException {
        Appointment appointment = this.appointmentRepository.getAppointment(appointmentId)
                .orElseThrow( () ->
                    AppointmentNotFoundException.notFound(appointmentId)
                );

        appointment.cancel();

        this.appointmentRepository.save(appointment);
    }

    public void completeAppointment(UUID appointmentId) throws AppointmentNotFoundException {
        Appointment appointment = this.appointmentRepository.getAppointment(appointmentId)
                .orElseThrow( () ->
                        AppointmentNotFoundException.notFound(appointmentId)
                );

        appointment.complete();

        this.appointmentRepository.save(appointment);
    }
}
