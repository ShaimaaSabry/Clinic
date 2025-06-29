package shaimaa.clinic.booking.internal.domain.contracts;

import shaimaa.clinic.booking.internal.domain.model.Slot;

import java.util.UUID;

public interface BookingPublisher {
    void publish(Slot slot, UUID patientId);
}
