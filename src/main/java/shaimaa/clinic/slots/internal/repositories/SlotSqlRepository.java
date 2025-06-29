package shaimaa.clinic.slots.internal.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public Optional<SlotEntity> getSlotById(UUID slotId) {
        return Optional.ofNullable(this.entityManager.find(SlotEntity.class, slotId));
    }

    @Transactional(transactionManager = "slotsTransactionManager")
    public void create(SlotEntity slotEntity) {
        this.entityManager.persist(slotEntity);
    }

    @Transactional(transactionManager = "slotsTransactionManager")
    public void update(SlotEntity slotEntity) {
        this.entityManager.merge(slotEntity);
    }
}
