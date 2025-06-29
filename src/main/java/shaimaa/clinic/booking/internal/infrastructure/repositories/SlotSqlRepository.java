package shaimaa.clinic.booking.internal.infrastructure.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shaimaa.clinic.booking.internal.domain.contracts.SlotRepository;
import shaimaa.clinic.booking.internal.domain.model.Slot;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component("bookingSlotSqlRepository")
class SlotSqlRepository implements SlotRepository {
    @PersistenceContext(unitName = "booking")
    private EntityManager entityManager;

    @Override
    @Transactional(transactionManager = "bookingTransactionManager")
    public void save(Slot slot) {
        SlotEntity slotEntity = SlotEntity.from(
                slot.getId(),
                slot.getStartsAt(),
                slot.getCost()
        );

        this.entityManager.persist(slotEntity);
    }

    @Override
    public List<Slot> getAvailableSlots() {
        String sql = "%s %s".formatted(
                "SELECT *",
                "FROM slot"
        );

        List<SlotEntity> slotEntities = entityManager.createNativeQuery(sql, SlotEntity.class).getResultList();

        return slotEntities
                .stream()
                .map(SlotEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<Slot> getSlotById(UUID slotId) {
        SlotEntity slotEntity = this.entityManager.find(SlotEntity.class, slotId);
        return Optional.ofNullable(
                slotEntity.toDomain()
        );
    }

    @Override
    @Transactional("bookingTransactionManager")
    public void deleteSlot(UUID slotId) {
        SlotEntity slotEntity = this.entityManager.find(SlotEntity.class, slotId);

        this.entityManager.remove(slotEntity);
    }
}
