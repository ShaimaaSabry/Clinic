package shaimaa.clinic.booking.internal.domain.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Patient {
    private final UUID id;

    private final String firstName;

    private final String lastName;

    private Patient(
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
