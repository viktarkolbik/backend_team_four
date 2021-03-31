CREATE extension IF NOT EXISTS "uuid-ossp";
INSERT INTO internship(id, name, "startDate", "endDate", "publicationDate", "techSkills", "country_ID",
                       "formatOfInternship_ID", "technology_ID", "formList_ID", "techExpert_ID", "admin_ID",
                       description, requirements, capacity, "registrationStartDate", "registrationEndDate")
VALUES (uuid_generate_v4(), 'name', '2021-03-31T10:40:00', '2021-03-31T10:40:00',
        '2021-03-31T10:40:00', 'text', uuid_generate_v4(), uuid_generate_v4(),
        uuid_generate_v4(), uuid_generate_v4(),
        uuid_generate_v4(), uuid_generate_v4(), 'text', 'text', 123,
        '2021-03-31T10:40:00', '2021-03-31T10:40:00');