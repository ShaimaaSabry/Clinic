package shaimaa.clinic.booking.internal.presentation.eventHandlers;

import org.springframework.stereotype.Component;
import shaimaa.clinic.booking.internal.application.commands.CreateSlot.CreateSlotCommand;
import shaimaa.clinic.booking.internal.application.commands.CreateSlot.CreateSlotHandler;
import shaimaa.clinic.shared.domain.EventBusImpl;
import shaimaa.clinic.slots.shared.dtos.SlotCreatedDto;

@Component("bookingSlotCreatedHandler")
public class SlotCreatedHandler {
    private final EventBusImpl eventBus;
    private final CreateSlotHandler createSlotHandler;

public SlotCreatedHandler(
        EventBusImpl eventBus,
        CreateSlotHandler createSlotHandler
) {
    this.eventBus = eventBus;
    this.eventBus.subscribe(
            SlotCreatedDto.class,
            this::handle
    );
    this.createSlotHandler = createSlotHandler;
}

    void handle(SlotCreatedDto dto) {
        this.createSlotHandler.handle(
                new CreateSlotCommand(
                        dto.id(),
                        dto.startsAt(),
                        dto.cost()
                )
        );
    }
}
