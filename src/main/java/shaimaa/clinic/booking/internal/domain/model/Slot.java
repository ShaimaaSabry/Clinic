package shaimaa.clinic.booking.internal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Slot {
    private UUID id;

    private LocalDateTime startsAt;

    private Float cost;

    private Slot(
            UUID id,
            LocalDateTime startsAt,
            Float cost
    ) {
        this.id = id;
        this.startsAt = startsAt;
        this.cost = cost;
    }

    public static Slot newSlot(
            UUID id,
            LocalDateTime startsAt,
            Float cost
    ) {
        return new Slot(
                id,
                startsAt,
                cost
        );
    }

    public static Slot from(
            UUID id,
            LocalDateTime startsAt,
            Float cost
    ) {
        return new Slot(
                id,
                startsAt,
                cost
        );
    }
}
