package shaimaa.clinic.slots.internal.service;

import shaimaa.clinic.slots.internal.repository.SlotEntity;
import shaimaa.clinic.slots.internal.repository.SlotSqlRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SlotService {
    private final SlotSqlRepository slotSqlRepository;

    SlotService(final SlotSqlRepository slotSqlRepository) {
        this.slotSqlRepository = slotSqlRepository;
    }

    public List<SlotEntity> getSlots() {
        return this.slotSqlRepository.getSlots();
    }

    public SlotEntity createSlot(SlotEntity slotEntity) {
        slotEntity.setReserved(false);
        this.slotSqlRepository.createSlot(slotEntity);
        return slotEntity;
    }
}
