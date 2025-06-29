package shaimaa.clinic.slots.shared;

import java.util.List;
import java.util.UUID;

public interface SlotsFacade {
    List<SlotResponse> getAvailableSlots();

    SlotResponse getSlotById(UUID slotId);
}
