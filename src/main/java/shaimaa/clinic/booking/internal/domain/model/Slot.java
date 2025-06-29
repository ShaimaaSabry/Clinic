package shaimaa.clinic.booking.internal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Slot {
    private UUID id;

    private LocalDateTime startsAt;

    private Float cost;

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
