INSERT INTO "user_detail" (u_id, u_firstname, u_lastname, u_email, u_login, u_password, u_role)
VALUES ('44444444-1111-1111-1111-789d2237f933', 'Vlad', 'Porsh', 'igor@gmail.com', 'roma', '1', 'ADMIN') ON CONFLICT (u_id) DO NOTHING;
INSERT INTO "user_skill" (us_u_id, us_name)
VALUES ('44444444-1111-1111-1111-789d2237f933', 'JAVA') ON CONFLICT (us_u_id, us_name) DO NOTHING;
INSERT INTO "user_internship" (ui_u_id, ui_inship_id)
VALUES ('44444444-1111-1111-1111-789d2237f933', '1069033e-e317-407f-be0a-3adc99ae844c') ON CONFLICT (ui_u_id, ui_inship_id) DO NOTHING;
