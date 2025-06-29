package shaimaa.clinic.slots.internal.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component("slotsSlotSqlRepository")
public class SlotSqlRepository {
    @PersistenceContext(unitName = "slots")
    private EntityManager entityManager;

    public List<SlotEntity> getSlots() {
        String sql = "%s %s".formatted(
                "SELECT *",
                "FROM slot"
        );

        List<Object[]> results = entityManager.createNativeQuery(sql).getResultList();

        List<SlotEntity> slots = new ArrayList<>();
        for (Object[] row : results) {
            SlotEntity slot = new SlotEntity();
            slot.setId((UUID) row[0]);
            slot.setStartsAt(((java.sql.Timestamp) row[1]).toLocalDateTime());
            slot.setReserved((Boolean) row[2]);
            slot.setCost(((Number) row[3]).floatValue());
            slots.add(slot);
        }

        return slots;
    }

    @Transactional
    public void createSlot(SlotEntity slotEntity) {
        this.entityManager.persist(slotEntity);
    }
}
