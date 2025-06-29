package shaimaa.clinic.appointments.internal.shell.api.v1;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import shaimaa.clinic.appointments.internal.core.exceptions.AppointmentNotFoundException;
import shaimaa.clinic.appointments.internal.core.model.Appointment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import shaimaa.clinic.appointments.internal.core.model.AppointmentStatus;
import shaimaa.clinic.appointments.internal.core.model.Patient;
import shaimaa.clinic.appointments.internal.core.services.AppointmentService;
import shaimaa.clinic.appointments.internal.shell.api.v1.GetAppointments.GetAppointmentsResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/appointments")
@Tag(name = "Appointments", description = "Manage the patients' appointments.")
class AppointmentController {
    private static final Logger LOG = LoggerFactory.getLogger(AppointmentController.class);

    private final AppointmentService appointmentService;

    AppointmentController(final AppointmentService appointmentService) {
        this.appointmentService= appointmentService;
    }

    @GetMapping("upcoming")
    GetAppointmentsResponse getUpcomingAppointments() {
        List<Appointment> appointments = this.appointmentService.getUpcomingAppointments();

        LOG.debug("Appointments: {}", appointments);

        return GetAppointmentsResponse.of(appointments);
    }

    @PatchMapping("{appointmentId}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void cancelAppointment(@PathVariable UUID appointmentId) throws AppointmentNotFoundException {
        this.appointmentService.cancelAppointment(appointmentId);
    }

    @PatchMapping("{appointmentId}/complete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void completeAppointment(@PathVariable UUID appointmentId) throws AppointmentNotFoundException {
        this.appointmentService.completeAppointment(appointmentId);
    }
}
