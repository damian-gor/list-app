INSERT INTO roles(name) VALUES('ROLE_USER'); -- 1
INSERT INTO roles(name) VALUES('ROLE_ADMIN'); -- 2

INSERT INTO users(email,password,username) VALUES ('damian.gorka94@gmail.com','$2a$10$/LONzloFszZa7d19s2vcAOA.dkiU5qbjqmaUFJCxgQMJ.0VnqFtk2','Damian');
INSERT INTO users(email,password,username) VALUES ('daria@gmail.com','$2a$10$/LONzloFszZa7d19s2vcAOA.dkiU5qbjqmaUFJCxgQMJ.0VnqFtk2','Daria');
INSERT INTO users(email,password,username) VALUES ('ania@gmail.com','$2a$10$/LONzloFszZa7d19s2vcAOA.dkiU5qbjqmaUFJCxgQMJ.0VnqFtk2','Ania');

INSERT INTO user_roles(user_id,role_id) VALUES (1,1);
INSERT INTO user_roles(user_id,role_id) VALUES (1,2);
INSERT INTO user_roles(user_id,role_id) VALUES (2,1);
INSERT INTO user_roles(user_id,role_id) VALUES (3,1);
