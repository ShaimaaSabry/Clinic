package shaimaa.clinic.slots.internal.domain.service;

import shaimaa.clinic.slots.internal.publishers.SlotsPublisher;
import shaimaa.clinic.slots.internal.repositories.SlotEntity;
import shaimaa.clinic.slots.internal.repositories.SlotSqlRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SlotService {
    private final SlotSqlRepository slotSqlRepository;
    private final SlotsPublisher slotsPublisher;

    SlotService(
            final SlotSqlRepository slotSqlRepository,
            final SlotsPublisher slotsPublisher
    ) {
        this.slotSqlRepository = slotSqlRepository;
        this.slotsPublisher = slotsPublisher;
    }

    public List<SlotEntity> getSlots() {
        return this.slotSqlRepository.getSlots();
    }

    public SlotEntity createSlot(SlotEntity slot) {
        slot.setReserved(false);
        this.slotSqlRepository.create(slot);

        this.slotsPublisher.publish(slot);

        return slot;
    }

    public void reserveSlot(UUID slotId) {
        SlotEntity slot = this.slotSqlRepository.getSlotById(slotId).get();
        slot.setReserved(true);
        this.slotSqlRepository.update(slot);
    }
}
