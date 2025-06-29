CREATE TYPE appointment_status AS ENUM ('SCHEDULED', 'COMPLETED', 'CANCELLED');

CREATE TABLE appointment (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    slot_id UUID NOT NULL,
    slot_starts_at TIMESTAMP NOT NULL,
    slot_cost NUMERIC(6,2) NOT NULL,
    patient_id UUID NOT NULL,
    status appointment_status NOT NULL DEFAULT 'SCHEDULED',
    FOREIGN KEY (patient_id) REFERENCES patient(id)
);