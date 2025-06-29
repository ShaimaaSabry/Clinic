package shaimaa.clinic.booking.internal.infrastructure.repositories;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
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

    static SlotEntity from(
            UUID id,
            LocalDateTime startsAt,
            Float cost
    ) {
        SlotEntity slotEntity = new SlotEntity();
        slotEntity.id = id;
        slotEntity.startsAt = startsAt;
        slotEntity.cost = cost;

        return slotEntity;
    }

    Slot toDomain() {
        return Slot.from(
                id,
                startsAt,
                cost
        );
    }
}
