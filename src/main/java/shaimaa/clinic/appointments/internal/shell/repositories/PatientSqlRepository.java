package shaimaa.clinic.appointments.internal.shell.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import shaimaa.clinic.appointments.internal.core.contracts.PatientRepository;
import shaimaa.clinic.appointments.internal.core.model.Patient;

import java.util.Optional;
import java.util.UUID;

@Component
class PatientSqlRepository implements PatientRepository {
    @PersistenceContext(unitName = "appointments")
    private EntityManager entityManager;

    @Override
    public Optional<Patient> getPatientById(UUID patientId) {
        PatientEntity patientEntity = this.entityManager.find(PatientEntity.class, patientId);

        return Optional.ofNullable(
            patientEntity.toDomain()
        );
    }
}
