CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
INSERT INTO internship (inship_id, inship_name, inship_start_date, inship_end_date, inship_publication_date, inship_tech_skills, inship_description, inship_requirements, inship_capacity, inship_registration_start_date, inship_registration_end_date)
VALUES (uuid_generate_v4(), 'JAVA', '2021-07-21T00:00', '2021-09-21T00:00', '2021-07-01T00:00', 'Test', 'Test', 'Test', 300, '2021-07-01T00:00', '2021-07-01T00:00');
INSERT INTO internship (inship_id, inship_name, inship_start_date, inship_end_date, inship_publication_date, inship_tech_skills, inship_description, inship_requirements, inship_capacity, inship_registration_start_date, inship_registration_end_date)
VALUES (uuid_generate_v4(), 'JS', '2021-08-21T00:00', '2021-10-21T00:00', '2021-08-01T00:00', 'Test', 'Test', 'Test', 200, '2021-08-01T00:00', '2021-10-01T00:00');
