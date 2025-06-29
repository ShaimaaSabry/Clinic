package shaimaa.clinic.appointments.internal.shell.repositories;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import shaimaa.clinic.appointments.internal.core.model.Appointment;
import shaimaa.clinic.appointments.internal.core.model.AppointmentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "appointment")
public class AppointmentEntity {
    @Id
    UUID id;

    @Column(name = "slot_id")
    UUID slotId;

    @Column(name = "slot_starts_at")
    LocalDateTime slotStartsAt;

    @Column(name = "slot_cost")
    Float slotCost;

    @ManyToOne
    PatientEntity patient;

//    @Column(name = "status")
//    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    AppointmentStatus status;

    static AppointmentEntity from(Appointment appointment) {
        return AppointmentEntity
                .builder()
                .id(appointment.getId())
                .slotId(appointment.getSlotId())
                .slotStartsAt(appointment.getSlotStartsAt())
                .slotCost(appointment.getSlotCost())
                .patient(
                       PatientEntity
                               .builder()
                               .id(appointment.getPatient().getId())
                               .build()
                )
                .status(appointment.getStatus())
                .build();
    }

    Appointment toDomain() {
        return Appointment.of(
                this.id,
                this.slotId,
                this.slotStartsAt,
                this.slotCost,
                this.patient != null ? this.patient.toDomain() : null,
                this.status
        );
    }
}
