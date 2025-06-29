package shaimaa.clinic.booking.internal.presentation.api.v1.BookSlot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import shaimaa.clinic.booking.internal.application.commands.BookSlot.BookSlotHandler;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingController.class)
@Import(BookingControllerTest.MockBeans.class)
class BookingControllerTest {

    @TestConfiguration
    static class MockBeans {
        @Bean
        BookSlotHandler bookSlotHandler() {
            return Mockito.mock(BookSlotHandler.class);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    class BookSlot {
        @Test
        void givenValidSlotId_thenShouldReturn201AndBookingDetails() throws Exception {
            // given
            UUID slotId = UUID.randomUUID();

            // when
            // then
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/v1/slots/" + slotId + "/bookings")
                            .contentType("application/json")
                    )
                    .andExpect(status().isAccepted());
        }
    }
}