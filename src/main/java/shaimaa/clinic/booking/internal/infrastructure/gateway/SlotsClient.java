package shaimaa.clinic.booking.internal.infrastructure.gateway;

import shaimaa.clinic.slots.shared.SlotsFacade;

public class SlotsClient {
    private final SlotsFacade slotsFacade;

    SlotsClient(final SlotsFacade slotsFacade) {
        this.slotsFacade = slotsFacade;
    }
}
