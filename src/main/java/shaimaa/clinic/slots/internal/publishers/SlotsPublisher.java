package shaimaa.clinic.slots.internal.publishers;

import org.springframework.stereotype.Component;
import shaimaa.clinic.shared.domain.EventBusImpl;
import shaimaa.clinic.slots.internal.repositories.SlotEntity;
import shaimaa.clinic.slots.shared.dtos.SlotCreatedDto;

@Component
public class SlotsPublisher {
    private final EventBusImpl eventBus;

    SlotsPublisher(EventBusImpl eventBus) {
        this.eventBus = eventBus;
    }

    public void publish(SlotEntity slot) {
        this.eventBus.push(
                new SlotCreatedDto(
                        slot.getId(),
                        slot.getStartsAt(),
                        slot.getCost()
                )
        );
    }
}
