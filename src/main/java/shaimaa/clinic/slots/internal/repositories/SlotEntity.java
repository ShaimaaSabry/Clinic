package shaimaa.clinic.slots.internal.repositories;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "slot")
public class SlotEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "starts_at")
    private LocalDateTime startsAt;

    @Column(name = "is_reserved")
    private boolean isReserved;

    private float cost;
}
