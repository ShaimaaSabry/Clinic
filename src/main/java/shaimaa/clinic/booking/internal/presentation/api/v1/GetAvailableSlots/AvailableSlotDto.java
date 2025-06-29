package shaimaa.clinic.booking.internal.presentation.api.v1.GetAvailableSlots;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import shaimaa.clinic.booking.internal.domain.model.Slot;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(hidden = true)
public record AvailableSlotDto(
        UUID id,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime startsAt,

        Float cost
) {
    static AvailableSlotDto from(Slot slot) {
        return new AvailableSlotDto(
                slot.getId(),
                slot.getStartsAt(),
                slot.getCost()
        );
    }
}
