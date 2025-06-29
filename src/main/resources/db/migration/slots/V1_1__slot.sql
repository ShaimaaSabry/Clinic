CREATE TABLE slot (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    starts_at TIMESTAMP NOT NULL,
    is_reserved BOOLEAN NOT NULL DEFAULT FALSE,
    cost NUMERIC(6,2) NOT NULL
);