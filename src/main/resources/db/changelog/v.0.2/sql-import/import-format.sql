INSERT INTO "format" (f_name)
VALUES ('ONLINE') ON CONFLICT (f_name) DO NOTHING;
INSERT INTO "format" (f_name)
VALUES ('OFFLINE') ON CONFLICT (f_name) DO NOTHING;

