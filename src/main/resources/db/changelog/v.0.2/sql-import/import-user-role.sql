INSERT INTO "user_role" (ur_id, ur_name)
VALUES ('6b1995ce-b7d4-4a6f-921f-43f5c775da10', 'ADMIN') ON CONFLICT (ur_id) DO NOTHING;
INSERT INTO "user_role" (ur_id, ur_name)
VALUES ('6829208c-404e-4355-9d29-ceaf8d321f18', 'SUPER-ADMIN') ON CONFLICT (ur_id) DO NOTHING;
INSERT INTO "user_role" (ur_id, ur_name)
VALUES ('776f21fa-feca-43c1-833f-a82fc593364f', 'TECH_EXPERT') ON CONFLICT (ur_id) DO NOTHING;
