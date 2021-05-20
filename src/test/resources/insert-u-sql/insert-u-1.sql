INSERT INTO "user_detail" (u_id, u_firstname, u_lastname, u_email, u_login, u_password, u_role)
VALUES ('11111111-1111-1111-1111-789d2237f933', 'Roma', 'Kopyl', 'roma@gmail.com', 'roma', '1', 'ADMIN') ON CONFLICT (u_id) DO NOTHING;
INSERT INTO "user_skill" (us_u_id, us_name)
VALUES ('11111111-1111-1111-1111-789d2237f933', 'JAVA') ON CONFLICT (us_u_id, us_name) DO NOTHING;
INSERT INTO "user_internship" (ui_u_id, ui_inship_id)
VALUES ('11111111-1111-1111-1111-789d2237f933', '1069033e-e317-407f-be0a-3adc99ae844c') ON CONFLICT (ui_u_id, ui_inship_id) DO NOTHING;

INSERT INTO user_time_slot (ust_id, ust_start_date, ust_end_date, ust_u_id)
VALUES ('11111111-120f-4419-b5a7-211cb7a8b034', '2021-06-02T11:00:00', '2021-06-02T11:30:00', '11111111-1111-1111-1111-789d2237f933')
ON CONFLICT (ust_id) DO NOTHING;