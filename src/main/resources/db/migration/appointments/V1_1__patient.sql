CREATE TABLE patient (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

INSERT INTO patient (id, first_name, last_name) VALUES
('3fa85f64-5717-4562-b3fc-2c963f66afa1', 'John', 'Doe'),
('3fa85f64-5717-4562-b3fc-2c963f66afa2', 'Jane', 'Smith'),
('3fa85f64-5717-4562-b3fc-2c963f66afa3', 'Alice', 'Johnson'),
('3fa85f64-5717-4562-b3fc-2c963f66afa4', 'Bob', 'Brown'),
('3fa85f64-5717-4562-b3fc-2c963f66afa5', 'Charlie', 'Davis');