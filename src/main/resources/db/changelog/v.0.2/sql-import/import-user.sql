INSERT INTO "user" (u_id, u_firstname, u_lastname, u_email, u_login, u_password, u_role)
VALUES ('b64b3afc-b1be-4c7a-9406-d7d14f436332', 'Maxim', 'Maevsky', 'maevsky@exadel.com', 'maevsky', '1', 'SUPER_ADMIN') ON CONFLICT (u_id) DO NOTHING;
INSERT INTO "user" (u_id, u_firstname, u_lastname, u_email, u_login, u_password, u_role)
VALUES ('740f8b07-3d75-4312-8ba9-1b8531f3940b', 'Andrey', 'Lopatenko', 'lopatenko@exadel.com', 'lopatenko', '1', 'ADMIN') ON CONFLICT (u_id) DO NOTHING;
INSERT INTO "user" (u_id, u_firstname, u_lastname, u_email, u_login, u_password, u_role)
VALUES ('7ae22ab0-93f9-4be5-9adb-4dbd5bf76c5c', 'Sergey', 'Ryzhkov', 'ryzhkov@exadel.com', 'ryzhkov', '1', 'ADMIN') ON CONFLICT (u_id) DO NOTHING;
INSERT INTO "user" (u_id, u_firstname, u_lastname, u_email, u_login, u_password, u_role)
VALUES ('20981ffb-2aa8-41fe-a631-2c6f225bb1e1', 'Lev', 'Petrenko', 'petrenko@exadel.com', 'petrenko', '1', 'ADMIN') ON CONFLICT (u_id) DO NOTHING;
INSERT INTO "user" (u_id, u_firstname, u_lastname, u_email, u_login, u_password, u_role)
VALUES ('e4ec8eef-c659-46f5-bde8-63551a980553', 'Bogdan', 'Vasiliev', 'vasiliev@exadel.com', 'vasiliev', '1', 'TECH_EXPERT') ON CONFLICT (u_id) DO NOTHING;