INSERT INTO form (fm_id, fm_firstname, fm_lastname, fm_middlename, fm_email, fm_phone_number, fm_skype, fm_experience, fm_education, fm_file_path, fm_primary_skill, fm_english_level, fm_form_status, fm_i_id, fm_country_id, fm_city_id)
VALUES ('7c0811d5-354b-4ebb-a65c-0b54efc53a80','Viktor','Glush','Viktorovich','viktor@mail.ru','+375448575692','testSkype1','testExp1','testEducation1','testPATH1','testSkills1', 'A2', 'REGISTERED', '5cbe6c4a-0be1-4016-83d9-e9e3222bb908', '21aab798-2d48-459c-bb84-789d2237f933','b38d8113-d5e8-4967-b8ff-e1df0844790f')
 ON CONFLICT (fm_id) DO NOTHING;

INSERT INTO internship (inship_id, inship_name, inship_start_date, inship_end_date, inship_publication_date, inship_tech_skills, inship_description, inship_requirements, inship_capacity, inship_registration_start_date, inship_registration_end_date, inship_format_name)
VALUES ('68a051d7-6d82-4879-b0a3-1340e14db54d', 'some name', '2021-07-21T00:00', '2021-09-21T00:00', '2021-07-01T00:00', 'skills1', 'description1', 'requirements1', 300, '2021-07-01T00:00', '2021-07-01T00:00', 'ONLINE') ON CONFLICT (inship_id) DO NOTHING;

INSERT INTO "user_detail" (u_id, u_firstname, u_lastname, u_email, u_login, u_password, u_role)
VALUES ('b64b3afc-b1be-4c7a-9406-d7d14f436332', 'Maxim', 'Maevsky', 'maevsky@exadel.com', 'maevsky', '1', 'SUPER_ADMIN') ON CONFLICT (u_id) DO NOTHING;
