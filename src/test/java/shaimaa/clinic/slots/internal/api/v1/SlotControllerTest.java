package shaimaa.clinic.slots.internal.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import shaimaa.clinic.slots.internal.domain.service.SlotService;
import shaimaa.clinic.slots.internal.repositories.SlotEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SlotController.class)
@Import(SlotControllerTest.MockBeans.class)
class SlotControllerTest {

    @TestConfiguration
    static class MockBeans {
        @Bean
        public SlotService slotService() {
            return Mockito.mock(SlotService.class);
        }
    }

    @Autowired
    private SlotService mockSlotService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    class GetSlots {

        @Test
        void shouldReturn200AndSlots() throws Exception {
            // given
            SlotEntity slot = createValidSlot();

            Mockito.when(mockSlotService.getSlots()).thenReturn(List.of(slot));

            // when
            RequestBuilder request = MockMvcRequestBuilders.get("/v1/slots");

            // then
            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.slots").isArray())
                    .andExpect(jsonPath("$.slots.length()", is(1)))
                    .andExpect(jsonPath("$.slots[0].id", is(slot.getId().toString())))
                    .andExpect(jsonPath("$.slots[0].startsAt", is(slot.getStartsAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))))
                    .andExpect(jsonPath("$.slots[0].isReserved", is(slot.isReserved())))
                    .andExpect(jsonPath("$.slots[0].cost", closeTo(slot.getCost(), 0.01)));
        }
    }

    @Nested
    class CreateSlot {

        @Test
        void givenValidRequest_thenShouldReturn201AndCreatedSlot() throws Exception {
            // given
            SlotEntity slot = createValidSlot();

            Mockito.when(mockSlotService.createSlot(Mockito.any(SlotEntity.class))).thenReturn(slot);

            // when
            Map<String, Object> requestPayload = Map.of(
                    "startsAt", slot.getStartsAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    "cost", slot.getCost()
            );

            RequestBuilder request = MockMvcRequestBuilders
                    .post("/v1/slots")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestPayload));

            // then
            mockMvc.perform(request)
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id", is(slot.getId().toString())))
                    .andExpect(jsonPath("$.startsAt", is(
                            slot.getStartsAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                            ))))
                    .andExpect(jsonPath("$.isReserved", is(false)))
                    .andExpect(jsonPath("$.cost", closeTo(slot.getCost(), 0.01)));
        }
    }

    private SlotEntity createValidSlot() {
        return SlotEntity.builder()
                .id(UUID.randomUUID())
                .startsAt(LocalDateTime.of(2025, 7, 20, 9, 0, 0))
                .cost(100.0f)
                .build();
    }
}
