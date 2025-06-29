package shaimaa.clinic.appointments.internal.core.contracts;

import shaimaa.clinic.appointments.internal.core.model.Patient;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository {
    Optional<Patient> getPatientById(UUID patientId);
}
