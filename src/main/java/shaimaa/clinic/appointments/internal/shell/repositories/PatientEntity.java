package shaimaa.clinic.appointments.internal.shell.repositories;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import shaimaa.clinic.appointments.internal.core.model.Patient;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "patient")
public class PatientEntity{
        @Id
        private UUID id;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

    Patient toDomain() {
        return Patient.of(
                this.id,
                this.firstName,
                this.lastName
        );
    }
}
