package shaimaa.clinic.slots.shared;

import shaimaa.clinic.slots.internal.service.SlotService;
import shaimaa.clinic.slots.internal.repository.SlotEntity;

import java.util.List;
import java.util.UUID;

class SlotsFacadeImpl implements SlotsFacade {
    private final SlotService slotService;

    SlotsFacadeImpl(final SlotService slotService) {
        this.slotService = slotService;
    }

    @Override
    public List<SlotResponse> getAvailableSlots() {
        return null;
//        List<SlotEntity> slots = this.slotService.getAvailableSlots();
//
//        return slots
//                .stream()
//                .map(SlotResponse::from)
//                .toList();
    }

    @Override
    public SlotResponse getSlotById(UUID slotId) {
        return null;
    }
}
