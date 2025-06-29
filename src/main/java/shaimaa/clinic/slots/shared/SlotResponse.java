package shaimaa.clinic.slots.shared;

import shaimaa.clinic.slots.internal.repository.SlotEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public record SlotResponse(
        UUID id,
        LocalDateTime startsAt,
        UUID doctorId,
        String doctorName
) {

    static SlotResponse from(SlotEntity slotEntity) {
        return new SlotResponse(
                slotEntity.getId(),
                slotEntity.getStartsAt(),
null,
null
//                slotEntity.doctor().id(),
//                slotEntity.doctor().name()
        );
    }
}
