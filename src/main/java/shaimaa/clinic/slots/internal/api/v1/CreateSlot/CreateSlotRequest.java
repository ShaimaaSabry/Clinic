package shaimaa.clinic.slots.internal.api.v1.CreateSlot;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import shaimaa.clinic.slots.internal.repository.SlotEntity;

import java.time.LocalDateTime;

public record CreateSlotRequest(
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime startsAt,

        @NotNull
        @PositiveOrZero
        Float cost
) {
    public SlotEntity toEntity() {
        return new SlotEntity(
                null, // ID will be generated by the database
                this.startsAt,
                false, // Default value for isReserved
                this.cost
        );
//        return SlotEntity.builder()
//                .startsAt(startsAt)
//                .cost(cost)
//                .build();
    }
}
