package shaimaa.clinic.booking.internal.presentation.api.v1.BookSlot;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shaimaa.clinic.booking.internal.application.commands.BookSlot.BookSlotCommand;
import shaimaa.clinic.booking.internal.application.commands.BookSlot.BookSlotHandler;

import java.util.UUID;

@RestController
@RequestMapping("v1/slots")
@Tag(name = "Bookings", description = "Manage the booking of available slots.")
public class BookingController {
    private final BookSlotHandler bookSlotHandler;

    BookingController(final  BookSlotHandler bookSlotHandler) {
        this.bookSlotHandler = bookSlotHandler;
    }

    @PostMapping("{slotId}/bookings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void book(@PathVariable UUID slotId) {
        BookSlotCommand command = new BookSlotCommand(slotId, UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa1"));
        this.bookSlotHandler.handle(command);
    }
}
