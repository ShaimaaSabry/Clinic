package shaimaa.clinic.booking.internal.domain.contracts;

import shaimaa.clinic.booking.internal.domain.model.Slot;

import java.util.List;
import java.util.UUID;

public interface SlotsGateway {
    List<Slot> getAvailableSlots(UUID doctorId);
}
