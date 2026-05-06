INSERT INTO plan (name, requests_per_minute, price)
VALUES
    ('FREE', 10, 0.00),
    ('PRO', 100, 29.00),
    ('ENTERPRISE', 1000, 199.00)
    ON CONFLICT (name) DO NOTHING;

INSERT INTO route_limit (plan_id, route_pattern, requests_per_minute)
SELECT id, '/api/products', 5 FROM plan WHERE name = 'FREE'
    ON CONFLICT DO NOTHING;

INSERT INTO route_limit (plan_id, route_pattern, requests_per_minute)
SELECT id, '/api/reports', 2 FROM plan WHERE name = 'FREE'
    ON CONFLICT DO NOTHING;

INSERT INTO admin_user (username, password, role)
VALUES (
           'admin',
           '$2a$10$VesL5BPpxoJCpR3IyPN58uSDxrCpElhhO0x0P38VrttzV2dk1js0i',
           'ADMIN'
       )
    ON CONFLICT (username) DO NOTHING;