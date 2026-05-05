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