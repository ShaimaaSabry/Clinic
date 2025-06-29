package shaimaa.clinic.booking.internal.application.queries.GetAvailableSlots;

import org.springframework.stereotype.Component;
import shaimaa.clinic.booking.internal.domain.contracts.SlotRepository;
import shaimaa.clinic.booking.internal.domain.contracts.SlotsGateway;
import shaimaa.clinic.booking.internal.domain.model.Slot;

import java.util.List;
import java.util.UUID;

@Component
public class GetAvailableSlotsHandler {
    private final SlotRepository slotRepository;

    GetAvailableSlotsHandler(
            final SlotRepository slotRepository
    ) {
        this.slotRepository = slotRepository;
    }

    public List<Slot> handle() {
        return this.slotRepository.getAvailableSlots();
    }
}
