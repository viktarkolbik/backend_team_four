INSERT INTO "interview" (i_id, i_admin_id, i_admin_feedback, i_tech_id, i_tech_feedback)
VALUES ('5cbe6c4a-0be1-4016-83d9-e9e3222bb908', 'b64b3afc-b1be-4c7a-9406-d7d14f436332', 'some feedback', null, null) ON CONFLICT (i_id) DO NOTHING;
INSERT INTO "interview" (i_id, i_admin_id, i_admin_feedback, i_tech_id, i_tech_feedback)
VALUES ('57570ac5-0e08-478c-87c3-3a6667ba4c5c', 'b64b3afc-b1be-4c7a-9406-d7d14f436332', 'some feedback', 'e4ec8eef-c659-46f5-bde8-63551a980553', null) ON CONFLICT (i_id) DO NOTHING;
