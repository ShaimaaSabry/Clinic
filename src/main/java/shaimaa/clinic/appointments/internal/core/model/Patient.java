package shaimaa.clinic.appointments.internal.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Patient {
    private final UUID id;

    private final String firstName;

    private final String lastName;

    Patient(
            UUID id,
            String firstName,
            String lastName
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Patient of(UUID id, String firstName, String lastName) {
        return new Patient(id, firstName, lastName);
    }
}
