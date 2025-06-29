package shaimaa.clinic.booking.internal.presentation.api.v1.GetAvailableSlots;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shaimaa.clinic.booking.internal.application.queries.GetAvailableSlots.GetAvailableSlotsHandler;
import shaimaa.clinic.booking.internal.domain.model.Slot;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/slots")
@Tag(name = "Slots", description = "Manage the doctor's available and reserved slots.")
class GetAvailableSlotsController {
    private final GetAvailableSlotsHandler getAvailableSlotsHandler;

    GetAvailableSlotsController(final GetAvailableSlotsHandler getAvailableSlotsHandler) {
        this.getAvailableSlotsHandler = getAvailableSlotsHandler;
    }

    @GetMapping("available")
    GetAvailableSlotsResponse getAvailableSlots() {
        List<Slot> slots = this.getAvailableSlotsHandler.handle();

        return GetAvailableSlotsResponse.of(slots);
    }
}
