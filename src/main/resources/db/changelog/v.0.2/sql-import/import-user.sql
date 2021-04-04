INSERT INTO "user" (user_id, user_firstname, user_lastname, user_email, user_login, user_password, user_role_id)
VALUES ('b64b3afc-b1be-4c7a-9406-d7d14f436332', 'Maxim', 'Maevsky', 'maevsky@exadel.com', 'maevsky', '1', '6b1995ce-b7d4-4a6f-921f-43f5c775da10') ON CONFLICT (user_id) DO NOTHING;
INSERT INTO "user" (user_id, user_firstname, user_lastname, user_email, user_login, user_password, user_role_id)
VALUES ('740f8b07-3d75-4312-8ba9-1b8531f3940b', 'Andrey', 'Lopatenko', 'lopatenko@exadel.com', 'lopatenko', '1', '6b1995ce-b7d4-4a6f-921f-43f5c775da10') ON CONFLICT (user_id) DO NOTHING;
INSERT INTO "user" (user_id, user_firstname, user_lastname, user_email, user_login, user_password, user_role_id)
VALUES ('7ae22ab0-93f9-4be5-9adb-4dbd5bf76c5c', 'Sergey', 'Ryzhkov', 'ryzhkov@exadel.com', 'ryzhkov', '1', '6829208c-404e-4355-9d29-ceaf8d321f18') ON CONFLICT (user_id) DO NOTHING;
INSERT INTO "user" (user_id, user_firstname, user_lastname, user_email, user_login, user_password, user_role_id)
VALUES ('20981ffb-2aa8-41fe-a631-2c6f225bb1e1', 'Lev', 'Petrenko', 'petrenko@exadel.com', 'petrenko', '1', '6829208c-404e-4355-9d29-ceaf8d321f18') ON CONFLICT (user_id) DO NOTHING;
INSERT INTO "user" (user_id, user_firstname, user_lastname, user_email, user_login, user_password, user_role_id)
VALUES ('e4ec8eef-c659-46f5-bde8-63551a980553', 'Bogdan', 'Vasiliev', 'vasiliev@exadel.com', 'vasiliev', '1', '776f21fa-feca-43c1-833f-a82fc593364f') ON CONFLICT (user_id) DO NOTHING;