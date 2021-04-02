INSERT INTO form (fm_id, fm_firstname, fm_lastname, fm_middlename, fm_email, fm_phone_number, fm_skype, fm_country, fm_city, fm_experience, fm_education, fm_file_path, fm_primary_skill)
VALUES ('7c0811d5-354b-4ebb-a65c-0b54efc53a80','Viktor','Glush','Viktorovich','viktor@mail.ru','+375448575692','testSkype1','Belarus','Gomel','testExp1','testEducation1','testPATH1','testSkills1') ON CONFLICT (fm_id) DO NOTHING;
INSERT INTO form (fm_id, fm_firstname, fm_lastname, fm_middlename, fm_email, fm_phone_number, fm_skype, fm_country, fm_city, fm_experience, fm_education, fm_file_path, fm_primary_skill)
VALUES ('52a6a4b2-5989-4323-ab27-9e9661e36da1','Ivan','Kopyl','Ivanovich','ivan@mail.ru','+375448255692','testSkype2','Belarus','Minsk','testExp2','testEducation2','testPATH2','testSkills2') ON CONFLICT (fm_id) DO NOTHING;
INSERT INTO form (fm_id, fm_firstname, fm_lastname, fm_middlename, fm_email, fm_phone_number, fm_skype, fm_country, fm_city, fm_experience, fm_education, fm_file_path, fm_primary_skill)
VALUES ('c95ae68c-f4a9-4319-b0f8-15a44b811cc9','Oleg','Gadlevsky','Olegovich','oleg@mail.ru','+375449575692','testSkype3','Belarus','Minsk','testExp3','testEducation3','testPATH3','testSkills3') ON CONFLICT (fm_id) DO NOTHING;
INSERT INTO form (fm_id, fm_firstname, fm_lastname, fm_middlename, fm_email, fm_phone_number, fm_skype, fm_country, fm_city, fm_experience, fm_education, fm_file_path, fm_primary_skill)
VALUES ('4bfa98dd-ca97-47d6-80ae-7386d9db301c','Petr','Kozlovsky','Petrovich','petr@mail.ru','+375448571111','testSkype4','Belarus','Vitebsc','testExp4','testEducation4','testPATH4','testSkills4') ON CONFLICT (fm_id) DO NOTHING;
INSERT INTO form (fm_id, fm_firstname, fm_lastname, fm_middlename, fm_email, fm_phone_number, fm_skype, fm_country, fm_city, fm_experience, fm_education, fm_file_path, fm_primary_skill)
VALUES ('2423ff67-46bd-41a0-a7e1-9c354b0ba83f','Aleksey','Chekanov','Alekseevich','aleksey@mail.ru','+375448222292','testSkype5','Belarus','Minsk','testExp5','testEducation5','testPATH5','testSkills5') ON CONFLICT (fm_id) DO NOTHING;
INSERT INTO form (fm_id, fm_firstname, fm_lastname, fm_middlename, fm_email, fm_phone_number, fm_skype, fm_country, fm_city, fm_experience, fm_education, fm_file_path, fm_primary_skill)
VALUES ('731a0e78-02e3-4683-896c-0ff2be75e6fb','Nikita','Koval','Nikitich','nikita@mail.ru','+375441115692','testSkype6','Belarus','Vitebsc','testExp6','testEducation6','testPATH6','testSkills6') ON CONFLICT (fm_id) DO NOTHING;
INSERT INTO form (fm_id, fm_firstname, fm_lastname, fm_middlename, fm_email, fm_phone_number, fm_skype, fm_country, fm_city, fm_experience, fm_education, fm_file_path, fm_primary_skill)
VALUES ('6578b840-0df8-4952-aafb-d6d8b3d9e9a7','Nikolay','Godyl','Nikolaevich','nikolay@mail.ru','+375448575555','testSkype7','Belarus','Gomel','testExp7','testEducation7','testPATH7','testSkills7') ON CONFLICT (fm_id) DO NOTHING;
INSERT INTO form (fm_id, fm_firstname, fm_lastname, fm_middlename, fm_email, fm_phone_number, fm_skype, fm_country, fm_city, fm_experience, fm_education, fm_file_path, fm_primary_skill)
VALUES ('878d0501-5bc0-4c26-974f-4c810cb636e9','Bob','Shelyto','Petrovich','bob@mail.ru','+375448575888','testSkype8','Belarus','Minsk','testExp8','testEducation8','testPATH8','testSkills8') ON CONFLICT (fm_id) DO NOTHING;
INSERT INTO form (fm_id, fm_firstname, fm_lastname, fm_middlename, fm_email, fm_phone_number, fm_skype, fm_country, fm_city, fm_experience, fm_education, fm_file_path, fm_primary_skill)
VALUES ('fe142fe5-7442-4b19-9bf5-322bfd9775a1','Alex','Siz','Olegovich','alex@mail.ru','+375448577792','testSkype9','Belarus','Vitebsc','testExp9','testEducation9','testPATH9','testSkills9') ON CONFLICT (fm_id) DO NOTHING;
INSERT INTO form (fm_id, fm_firstname, fm_lastname, fm_middlename, fm_email, fm_phone_number, fm_skype, fm_country, fm_city, fm_experience, fm_education, fm_file_path, fm_primary_skill)
VALUES ('095629a9-d976-4ac5-a58a-538067b26656','Roma','Sekach','Viktorovich','roma@mail.ru','+375441112223','testSkype10','Belarus','Gomel','testExp10','testEducation10','testPATH10','testSkills10') ON CONFLICT (fm_id) DO NOTHING;


INSERT INTO internship (inship_id, inship_name, inship_start_date, inship_end_date, inship_publication_date, inship_tech_skills, inship_description, inship_requirements, inship_capacity, inship_registration_start_date, inship_registration_end_date)
VALUES ('68a051d7-6d82-4879-b0a3-1340e14db54d', 'JAVA', '2021-07-21T00:00', '2021-09-21T00:00', '2021-07-01T00:00', 'skills1', 'description1', 'requirements1', 300, '2021-07-01T00:00', '2021-07-01T00:00') ON CONFLICT (inship_id) DO NOTHING;
INSERT INTO internship (inship_id, inship_name, inship_start_date, inship_end_date, inship_publication_date, inship_tech_skills, inship_description, inship_requirements, inship_capacity, inship_registration_start_date, inship_registration_end_date)
VALUES ('55fdfdd2-cac7-4851-a0a6-ba93113e926b', 'JS', '2021-08-21T00:00', '2021-10-21T00:00', '2021-08-01T00:00', 'skills2', 'description2', 'requirements2', 200, '2021-08-01T00:00', '2021-10-01T00:00') ON CONFLICT (inship_id) DO NOTHING;
INSERT INTO internship (inship_id, inship_name, inship_start_date, inship_end_date, inship_publication_date, inship_tech_skills, inship_description, inship_requirements, inship_capacity, inship_registration_start_date, inship_registration_end_date)
VALUES ('0137afd2-42ca-49e4-9018-3499199fdf43', 'DOT_NET', '2021-07-21T00:00', '2021-09-21T00:00', '2021-07-01T00:00', 'skills3', 'description3', 'requirements3', 150, '2021-07-01T00:00', '2021-07-01T00:00') ON CONFLICT (inship_id) DO NOTHING;
INSERT INTO internship (inship_id, inship_name, inship_start_date, inship_end_date, inship_publication_date, inship_tech_skills, inship_description, inship_requirements, inship_capacity, inship_registration_start_date, inship_registration_end_date)
VALUES ('967fdef2-18cd-4e77-aef0-7c831cd8263d', 'C_SHARP', '2021-08-21T00:00', '2021-10-21T00:00', '2021-08-01T00:00', 'skills4', 'description4', 'requirements4', 100, '2021-08-01T00:00', '2021-10-01T00:00') ON CONFLICT (inship_id) DO NOTHING;
INSERT INTO internship (inship_id, inship_name, inship_start_date, inship_end_date, inship_publication_date, inship_tech_skills, inship_description, inship_requirements, inship_capacity, inship_registration_start_date, inship_registration_end_date)
VALUES ('1069033e-e317-407f-be0a-3adc99ae844c', 'C_PLUS_PLUS', '2021-07-21T00:00', '2021-09-21T00:00', '2021-07-01T00:00', 'skills5', 'description5', 'requirements5', 50, '2021-07-01T00:00', '2021-07-01T00:00') ON CONFLICT (inship_id) DO NOTHING;
INSERT INTO internship (inship_id, inship_name, inship_start_date, inship_end_date, inship_publication_date, inship_tech_skills, inship_description, inship_requirements, inship_capacity, inship_registration_start_date, inship_registration_end_date)
VALUES ('89b7a3ef-8561-41ca-a9ff-14415d3dc80c', 'IOS', '2021-08-21T00:00', '2021-10-21T00:00', '2021-08-01T00:00', 'skills6', 'description6', 'requirements6', 200, '2021-08-01T00:00', '2021-10-01T00:00') ON CONFLICT (inship_id) DO NOTHING;
INSERT INTO internship (inship_id, inship_name, inship_start_date, inship_end_date, inship_publication_date, inship_tech_skills, inship_description, inship_requirements, inship_capacity, inship_registration_start_date, inship_registration_end_date)
VALUES ('e0620a56-897a-4616-95e8-5ed1d5314974', 'PYTHON', '2021-07-21T00:00', '2021-09-21T00:00', '2021-07-01T00:00', 'skills7', 'description7', 'requirements7', 100, '2021-07-01T00:00', '2021-07-01T00:00') ON CONFLICT (inship_id) DO NOTHING;
INSERT INTO internship (inship_id, inship_name, inship_start_date, inship_end_date, inship_publication_date, inship_tech_skills, inship_description, inship_requirements, inship_capacity, inship_registration_start_date, inship_registration_end_date)
VALUES ('7e571523-0c53-41b2-9b4d-2811bba896a2', 'TESTING', '2021-08-21T00:00', '2021-10-21T00:00', '2021-08-01T00:00', 'skills8', 'description8', 'requirements8', 50, '2021-08-01T00:00', '2021-10-01T00:00') ON CONFLICT (inship_id) DO NOTHING;
INSERT INTO internship (inship_id, inship_name, inship_start_date, inship_end_date, inship_publication_date, inship_tech_skills, inship_description, inship_requirements, inship_capacity, inship_registration_start_date, inship_registration_end_date)
VALUES ('8c888fc7-d126-4fe1-8023-b6071b7c7843', 'RUBY', '2021-07-21T00:00', '2021-09-21T00:00', '2021-07-01T00:00', 'skills9', 'description9', 'requirements9', 300, '2021-07-01T00:00', '2021-07-01T00:00') ON CONFLICT (inship_id) DO NOTHING;
INSERT INTO internship (inship_id, inship_name, inship_start_date, inship_end_date, inship_publication_date, inship_tech_skills, inship_description, inship_requirements, inship_capacity, inship_registration_start_date, inship_registration_end_date)
VALUES ('c48bf69e-f3a9-4ff0-a94a-f2a7f49a42dd', 'DEVOPS', '2021-08-21T00:00', '2021-10-21T00:00', '2021-08-01T00:00', 'skills10', 'description10', 'requirements10', 200, '2021-08-01T00:00', '2021-10-01T00:00') ON CONFLICT (inship_id) DO NOTHING;