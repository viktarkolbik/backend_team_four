INSERT INTO "interview_dates" (i_dates_id, i_dates_date_time, i_dates_user_id, i_dates_interview_id)
VALUES ('783ba66a-424b-433b-9584-20f3be9020e0', '2021-08-21T00:00', 'b64b3afc-b1be-4c7a-9406-d7d14f436332', '5cbe6c4a-0be1-4016-83d9-e9e3222bb908') ON CONFLICT (i_dates_id) DO NOTHING;
INSERT INTO "interview_dates" (i_dates_id, i_dates_date_time, i_dates_user_id, i_dates_interview_id)
VALUES ('9898c583-d0ab-4366-acb4-6174c1c4290e', '2021-06-18T00:00', 'e4ec8eef-c659-46f5-bde8-63551a980553', '57570ac5-0e08-478c-87c3-3a6667ba4c5c') ON CONFLICT (i_dates_id) DO NOTHING;
