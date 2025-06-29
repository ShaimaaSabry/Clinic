package shaimaa.clinic.slots.internal.repository;

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

    private LocalDateTime startsAt;

    private boolean isReserved;

    private float cost;
}
