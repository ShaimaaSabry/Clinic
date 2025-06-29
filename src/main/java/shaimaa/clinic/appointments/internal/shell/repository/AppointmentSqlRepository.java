package shaimaa.clinic.appointments.internal.shell.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shaimaa.clinic.appointments.internal.core.contracts.AppointmentRepository;
import shaimaa.clinic.appointments.internal.core.model.Appointment;
import shaimaa.clinic.slots.internal.repository.SlotEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AppointmentSqlRepository implements AppointmentRepository {
    private static final Logger LOG = LoggerFactory.getLogger(AppointmentSqlRepository.class);

    @PersistenceContext(unitName = "appointments")
    private EntityManager entityManager;

    @Override
    public List<Appointment> getUpcomingAppointments() {
        String sql = "%s %s".formatted(
                "SELECT *",
                "FROM appointment"
        );

        List<AppointmentEntity> appointmentEntities = entityManager.createNativeQuery(sql, AppointmentEntity.class).getResultList();

        LOG.debug("Retrieved: {}", appointmentEntities);

        return appointmentEntities
                .stream()
                .map(AppointmentEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<Appointment> getAppointment(UUID appointmentId) {
        AppointmentEntity appointmentEntity = this.entityManager.find(AppointmentEntity.class, appointmentId);

        return Optional.ofNullable(appointmentEntity).map(AppointmentEntity::toDomain);
    }

    @Override
    @Transactional("appointmentsTransactionManager")
    public void updateAppointment(Appointment appointment) {
        AppointmentEntity appointmentEntity = AppointmentEntity.from(appointment);

        this.entityManager.merge(appointmentEntity);
    }
}
