-- Sample seed data for development

-- Repair audit columns for existing tables that were created before BaseEntity had createdAt.
ALTER TABLE IF EXISTS users ADD COLUMN IF NOT EXISTS created_at timestamp NOT NULL DEFAULT now();
ALTER TABLE IF EXISTS passengers ADD COLUMN IF NOT EXISTS created_at timestamp NOT NULL DEFAULT now();
ALTER TABLE IF EXISTS buses ADD COLUMN IF NOT EXISTS created_at timestamp NOT NULL DEFAULT now();
ALTER TABLE IF EXISTS routes ADD COLUMN IF NOT EXISTS created_at timestamp NOT NULL DEFAULT now();
ALTER TABLE IF EXISTS trips ADD COLUMN IF NOT EXISTS created_at timestamp NOT NULL DEFAULT now();
ALTER TABLE IF EXISTS tickets ADD COLUMN IF NOT EXISTS created_at timestamp NOT NULL DEFAULT now();
ALTER TABLE IF EXISTS bookings ADD COLUMN IF NOT EXISTS created_at timestamp NOT NULL DEFAULT now();
ALTER TABLE IF EXISTS payments ADD COLUMN IF NOT EXISTS created_at timestamp NOT NULL DEFAULT now();

DROP TABLE IF EXISTS bus_staff;
DROP TABLE IF EXISTS staffs;

DELETE FROM users WHERE role = 'STAFF';

-- Users
INSERT INTO users (id, full_name, email, password, address, role, created_at, updated_at)
VALUES
  (1, 'System Admin', 'admin@sajiloyatra.com', 'Admin@123', 'Kathmandu', 'ADMIN', TIMESTAMP '2026-06-09 08:00:00', TIMESTAMP '2026-06-09 08:00:00'),
  (4, 'Anita Rai', 'anita.rai@sajiloyatra.com', 'Passenger@123', 'Pokhara', 'PASSENGER', TIMESTAMP '2026-06-09 08:03:00', TIMESTAMP '2026-06-09 08:03:00'),
  (5, 'Rajan Adhikari', 'rajan.adhikari@sajiloyatra.com', 'Passenger2@123', 'Bharatpur', 'PASSENGER', TIMESTAMP '2026-06-09 08:04:00', TIMESTAMP '2026-06-09 08:04:00')
ON CONFLICT DO NOTHING;

-- Buses
INSERT INTO buses (id, bus_number, capacity, bus_type_enum, bus_status_enum)
VALUES
  (1, 'BA-01-KTM', 36, 'PREMIUM', 'AVAILABLE'),
  (2, 'BA-02-KTM', 28, 'REGULAR', 'AVAILABLE'),
  (3, 'BA-03-PKR', 40, 'VIP', 'MAINTENANCE')
ON CONFLICT DO NOTHING;

-- Routes
INSERT INTO routes (id, source, destination, distance, estimated_duration)
VALUES
  (1, 'Kathmandu', 'Pokhara', 200, '6 hours'),
  (2, 'Kathmandu', 'Chitwan', 160, '5 hours')
ON CONFLICT DO NOTHING;

-- Passengers
INSERT INTO passengers (id, citizenship_no, preferences, user_id)
VALUES
  (1, '102-45-678901', 'Window seat, AC coach', 4),
  (2, '105-98-765432', 'Aisle seat, front row', 5)
ON CONFLICT DO NOTHING;

-- Trips
INSERT INTO trips (id, trip_date, departure_time, arrival_time, trip_status_enum, route_id, bus_id)
VALUES
  (1, DATE '2026-06-15', TIMESTAMP '2026-06-15 07:00:00', TIMESTAMP '2026-06-15 13:00:00', 'SCHEDULED', 1, 1),
  (2, DATE '2026-06-15', TIMESTAMP '2026-06-15 14:30:00', TIMESTAMP '2026-06-15 20:30:00', 'ONGOING', 1, 2),
  (3, DATE '2026-06-16', TIMESTAMP '2026-06-16 06:30:00', TIMESTAMP '2026-06-16 11:30:00', 'COMPLETED', 2, 3),
  (4, DATE '2026-06-17', TIMESTAMP '2026-06-17 18:00:00', TIMESTAMP '2026-06-17 23:30:00', 'CANCELLED', 2, 1)
ON CONFLICT DO NOTHING;

-- Tickets
INSERT INTO tickets (id, ticket_number, fare, issue_date)
VALUES
  (1, 'TKT-0001', 1200.00, TIMESTAMP '2026-06-09 09:20:00'),
  (2, 'TKT-0002', 1250.00, TIMESTAMP '2026-06-09 09:35:00'),
  (3, 'TKT-0003', 1000.00, TIMESTAMP '2026-06-09 09:50:00'),
  (4, 'TKT-0004', 1100.00, TIMESTAMP '2026-06-09 10:05:00')
ON CONFLICT DO NOTHING;

-- Bookings
INSERT INTO bookings (id, booking_date, booking_status_enum, booking_class_enum, passenger_id, trip_id, ticket_id)
VALUES
  (1, TIMESTAMP '2026-06-09 09:22:00', 'PENDING', 'REGULAR', 1, 1, 1),
  (2, TIMESTAMP '2026-06-09 09:37:00', 'CONFIRMED', 'PREMIUM', 2, 2, 2),
  (3, TIMESTAMP '2026-06-09 09:52:00', 'CANCELLED', 'VIP', 1, 3, 3),
  (4, TIMESTAMP '2026-06-09 10:07:00', 'CONFIRMED', 'REGULAR', 2, 4, 4)
ON CONFLICT DO NOTHING;

-- Payments
INSERT INTO payments (id, amount, payment_date, payment_method_enum, ticket_id)
VALUES
  (1, 1200.00, TIMESTAMP '2026-06-09 09:25:00', 'ESEWA', 1),
  (2, 1250.00, TIMESTAMP '2026-06-09 09:40:00', 'KHALTI', 2),
  (3, 1000.00, TIMESTAMP '2026-06-09 09:55:00', 'CARD', 3),
  (4, 1100.00, TIMESTAMP '2026-06-09 10:10:00', 'CASH', 4)
ON CONFLICT DO NOTHING;

-- Reset sequences so future inserts continue after the seed rows.
SELECT setval(pg_get_serial_sequence('users', 'id'), (SELECT COALESCE(MAX(id), 1) FROM users));
SELECT setval(pg_get_serial_sequence('passengers', 'id'), (SELECT COALESCE(MAX(id), 1) FROM passengers));
SELECT setval(pg_get_serial_sequence('buses', 'id'), (SELECT COALESCE(MAX(id), 1) FROM buses));
SELECT setval(pg_get_serial_sequence('routes', 'id'), (SELECT COALESCE(MAX(id), 1) FROM routes));
SELECT setval(pg_get_serial_sequence('trips', 'id'), (SELECT COALESCE(MAX(id), 1) FROM trips));
SELECT setval(pg_get_serial_sequence('tickets', 'id'), (SELECT COALESCE(MAX(id), 1) FROM tickets));
SELECT setval(pg_get_serial_sequence('bookings', 'id'), (SELECT COALESCE(MAX(id), 1) FROM bookings));
SELECT setval(pg_get_serial_sequence('payments', 'id'), (SELECT COALESCE(MAX(id), 1) FROM payments));
