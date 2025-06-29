package shaimaa.clinic.booking.internal.presentation.api.v1.GetAvailableSlots;

import shaimaa.clinic.booking.internal.domain.model.Slot;

import java.util.List;

public record GetAvailableSlotsResponse(
        List<AvailableSlotDto> slots
) {
    public static GetAvailableSlotsResponse of(List<Slot> slots) {
        return new GetAvailableSlotsResponse(
                slots.stream().map(AvailableSlotDto::from).toList()
        );
    }
}
