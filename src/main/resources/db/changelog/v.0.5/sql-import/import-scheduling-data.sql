INSERT INTO form (fm_id, fm_firstname, fm_lastname, fm_middlename, fm_email, fm_phone_number, fm_skype, fm_experience, fm_education, fm_file_path, fm_primary_skill, fm_country_id, fm_city_id,fm_english_level,fm_form_status, fm_inship_id)
VALUES ('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454','Nikita','Kucher','Egorovich','nikita@mail.ru','+375448575692','testSkype1','testExp1','testEducation1','testPATH1','testSkills1', '21aab798-2d48-459c-bb84-789d2237f933','b38d8113-d5e8-4967-b8ff-e1df0844790f','B1','REGISTERED', '967fdef2-18cd-4e77-aef0-7c831cd8263d')
 ON CONFLICT (fm_id) DO UPDATE SET  fm_country_id='21aab798-2d48-459c-bb84-789d2237f933', fm_city_id='b38d8113-d5e8-4967-b8ff-e1df0844790f', fm_english_level='B1', fm_form_status='REGISTERED', fm_inship_id='967fdef2-18cd-4e77-aef0-7c831cd8263d';

INSERT INTO user_time_slot (ust_id, ust_start_date, ust_end_date, ust_u_id)
VALUES ('2ca9583c-3617-45d6-8e57-d2c66c46e55e', '2021-04-28T10:00:00', '2021-04-28T10:30:00', '20981ffb-2aa8-41fe-a631-2c6f225bb1e1')
ON CONFLICT (ust_id) DO NOTHING;
INSERT INTO user_time_slot (ust_id, ust_start_date, ust_end_date, ust_u_id)
VALUES ('97721c48-5617-4fdb-80d0-6cc5ff90b201', '2021-04-28T10:30:00', '2021-04-28T11:00:00', '20981ffb-2aa8-41fe-a631-2c6f225bb1e1')
ON CONFLICT (ust_id) DO NOTHING;
INSERT INTO user_time_slot (ust_id, ust_start_date, ust_end_date, ust_u_id)
VALUES ('ce142456-120f-4419-b5a7-211cb7a8b034', '2021-04-28T11:00:00', '2021-04-28T11:30:00', '20981ffb-2aa8-41fe-a631-2c6f225bb1e1')
ON CONFLICT (ust_id) DO NOTHING;

INSERT INTO user_time_slot (ust_id, ust_start_date, ust_end_date, ust_u_id)
VALUES ('f341ddf7-5695-450f-94ec-8f1fc9827be3', '2021-04-28T20:00:00', '2021-04-28T20:30:00', 'e4ec8eef-c659-46f5-bde8-63551a980553')
ON CONFLICT (ust_id) DO NOTHING;
INSERT INTO user_time_slot (ust_id, ust_start_date, ust_end_date, ust_u_id)
VALUES ('0b5467d3-2ff6-4efc-9f41-cf54d8ad62bb', '2021-04-28T20:30:00', '2021-04-28T21:00:00', 'e4ec8eef-c659-46f5-bde8-63551a980553')
ON CONFLICT (ust_id) DO NOTHING;
INSERT INTO user_time_slot (ust_id, ust_start_date, ust_end_date, ust_u_id)
VALUES ('aa706aa6-56e9-4d1b-8174-71b084fee018', '2021-04-28T21:00:00', '2021-04-28T21:30:00', 'e4ec8eef-c659-46f5-bde8-63551a980553')
ON CONFLICT (ust_id) DO NOTHING;
