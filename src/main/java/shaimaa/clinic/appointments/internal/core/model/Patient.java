package shaimaa.clinic.appointments.internal.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class Patient {
    private UUID id;

    private String firstName;

    private String lastName;

    public static Patient from(UUID id, String firstName, String lastName) {
        return new Patient(id, firstName, lastName);
    }
}
