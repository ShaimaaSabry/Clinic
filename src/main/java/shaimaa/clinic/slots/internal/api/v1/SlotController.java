package shaimaa.clinic.slots.internal.api.v1;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import shaimaa.clinic.slots.internal.api.v1.CreateSlot.CreateSlotRequest;
import shaimaa.clinic.slots.internal.api.v1.GetSlots.GetSlotsResponse;
import shaimaa.clinic.slots.internal.api.v1.dto.SlotDto;
import shaimaa.clinic.slots.internal.domain.service.SlotService;
import shaimaa.clinic.slots.internal.repositories.SlotEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/slots")
@Tag(name = "Slots", description = "Manage the doctor's available and reserved slots.")
class SlotController {
    private final SlotService slotService;

    SlotController(final SlotService slotService) {
        this.slotService = slotService;
    }

    @GetMapping
    GetSlotsResponse getSlots() {
        List<SlotEntity> slots = this.slotService.getSlots();
        return GetSlotsResponse.of(slots);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SlotDto createSlot(@Valid @RequestBody CreateSlotRequest request) {
        SlotEntity slot =this.slotService.createSlot(request.toEntity());

        return SlotDto.of(slot);
    }
}
