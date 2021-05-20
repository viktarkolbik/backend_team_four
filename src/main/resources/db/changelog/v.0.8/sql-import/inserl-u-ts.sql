INSERT INTO user_time_slot (ust_id, ust_start_date, ust_end_date, ust_u_id)
VALUES ('1141ddf7-5695-450f-94ec-8f1fc9827be3', '2021-06-01T20:00:00', '2021-06-01T20:30:00', 'e4ec8eef-c659-46f5-bde8-63551a980553')
ON CONFLICT (ust_id) DO NOTHING;
INSERT INTO user_time_slot (ust_id, ust_start_date, ust_end_date, ust_u_id)
VALUES ('225467d3-2ff6-4efc-9f41-cf54d8ad62bb', '2021-06-01T20:30:00', '2021-06-01T21:00:00', 'e4ec8eef-c659-46f5-bde8-63551a980553')
ON CONFLICT (ust_id) DO NOTHING;
INSERT INTO user_time_slot (ust_id, ust_start_date, ust_end_date, ust_u_id)
VALUES ('33706aa6-56e9-4d1b-8174-71b084fee018', '2021-06-01T21:00:00', '2021-06-01T21:30:00', 'e4ec8eef-c659-46f5-bde8-63551a980553')
ON CONFLICT (ust_id) DO NOTHING;

INSERT INTO user_time_slot (ust_id, ust_start_date, ust_end_date, ust_u_id)
VALUES ('1ca9583c-3617-45d6-8e57-d2c66c46e55e', '2021-06-02T10:00:00', '2021-06-02T10:30:00', '20981ffb-2aa8-41fe-a631-2c6f225bb1e1')
ON CONFLICT (ust_id) DO NOTHING;
INSERT INTO user_time_slot (ust_id, ust_start_date, ust_end_date, ust_u_id)
VALUES ('27721c48-5617-4fdb-80d0-6cc5ff90b201', '2021-06-02T10:30:00', '2021-06-02T11:00:00', '20981ffb-2aa8-41fe-a631-2c6f225bb1e1')
ON CONFLICT (ust_id) DO NOTHING;
INSERT INTO user_time_slot (ust_id, ust_start_date, ust_end_date, ust_u_id)
VALUES ('3e142456-120f-4419-b5a7-211cb7a8b034', '2021-06-02T11:00:00', '2021-06-02T11:30:00', '20981ffb-2aa8-41fe-a631-2c6f225bb1e1')
ON CONFLICT (ust_id) DO NOTHING;