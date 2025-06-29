package shaimaa.clinic.booking.internal.infrastructure.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import shaimaa.clinic.booking.internal.domain.model.Slot;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "slot")
class SlotEntity {
        @Id
        private UUID id;

        @Column(name = "starts_at")
        private LocalDateTime startsAt;

        private float cost;

    Slot toDomain() {
        return new Slot(
                id,
                startsAt,
                cost
        );
    }
}
