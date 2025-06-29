package shaimaa.clinic.notifications.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
class NotificationsService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationsService.class);

    void sendNotificationToPatient(
            LocalDateTime slotStartTime
    ) {
        logger.info(
                "Sending notification to patient: Appointment at {}",
                slotStartTime
        );
    }

    void sendNotificationToDoctor(
            LocalDateTime slotStartTime,
            String patientFirstName,
            String patientLastName
    ) {
        logger.info(
                "Sending notification to doctor: Appointment with {} {} at {}",
                patientFirstName,
                patientLastName,
                slotStartTime
        );
    }
}
