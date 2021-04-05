INSERT INTO "interview" (i_id, u_admin_id, u_admin_feedback, u_tech_id, u_tech_feedback)
VALUES ('9349cd86-ae5b-43c0-83bc-2a16476b9e47', 'b64b3afc-b1be-4c7a-9406-d7d14f436332', 'some feedback', null, null) ON CONFLICT (i_id) DO NOTHING;
INSERT INTO "interview" (i_id, u_admin_id, u_admin_feedback, u_tech_id, u_tech_feedback)
VALUES ('5cc912bf-a95a-4186-8a78-b027d18f19f8', 'b64b3afc-b1be-4c7a-9406-d7d14f436332', 'some feedback', 'e4ec8eef-c659-46f5-bde8-63551a980553', null) ON CONFLICT (i_id) DO NOTHING;
