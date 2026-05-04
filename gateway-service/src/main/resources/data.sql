INSERT INTO plan (name, requests_per_minute, price)
VALUES
    ('FREE', 10, 0.00),
    ('PRO', 100, 29.00),
    ('ENTERPRISE', 1000, 199.00)
    ON CONFLICT (name) DO NOTHING;