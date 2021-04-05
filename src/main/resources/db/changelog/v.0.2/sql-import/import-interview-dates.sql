INSERT INTO "interview-dates" (i_dates_id, i_dates_date_time, user_id, interview_id)
VALUES ('4524be30-56fd-49c9-bb18-ad0b80184c73', '2021-08-21T00:00', 'b64b3afc-b1be-4c7a-9406-d7d14f436332', '9349cd86-ae5b-43c0-83bc-2a16476b9e47') ON CONFLICT (i_dates_id) DO NOTHING;
INSERT INTO "interview-dates" (i_dates_id, i_dates_date_time, user_id, interview_id)
VALUES ('9559706c-9e1c-4938-af4e-2ccb5e60f80e', '2021-06-18T00:00', 'e4ec8eef-c659-46f5-bde8-63551a980553', '5cc912bf-a95a-4186-8a78-b027d18f19f8') ON CONFLICT (i_dates_id) DO NOTHING;
