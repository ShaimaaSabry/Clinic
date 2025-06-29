package shaimaa.clinic.slots.internal.api.v1.GetSlots;

import shaimaa.clinic.slots.internal.api.v1.dto.SlotDto;
import shaimaa.clinic.slots.internal.repositories.SlotEntity;

import java.util.List;

public record GetSlotsResponse(
    List<SlotDto> slots
) {
    public static GetSlotsResponse of(List<SlotEntity> slots) {
        return new GetSlotsResponse(
                slots.stream().map(SlotDto::of).toList()
        );
    }
}
