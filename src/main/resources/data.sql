DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS authorization;

CREATE TABLE role (
  role_id INT AUTO_INCREMENT  PRIMARY KEY,
  role_title VARCHAR(250) NOT NULL,
  role_description VARCHAR(250) NOT NULL
);

INSERT INTO role (role_title, role_description) VALUES
  ('FORMATION', 'Formation'),
  ('QCM', 'Qcm'),
  ('EVENT', 'Event'),
  ('CERTIFICATION', 'Certification'),
  ('COLLABORATOR', 'Collaborateur'),
  ('ADMIN', 'Administrator');


CREATE TABLE account (
  account_mail_adresse VARCHAR(250) PRIMARY KEY,
  password  VARCHAR(250) NOT NULL,
  account_first_name VARCHAR(250) DEFAULT NULL,
  account_last_name VARCHAR(250)  DEFAULT NULL,
  role_id INT DEFAULT 0
);

INSERT INTO account (account_mail_adresse, password,account_first_name, account_last_name,role_id) VALUES
  ('yelouardi@sqli.com','123456','Yassine', 'Elouardi',6),
  ('zeljazouli@sqli.com','123456','Zakaria', 'El Jazouli',1),
  ('kladib@sqli.com','123456','Khalid', 'Labib',2),
  ('kkouis@sqli.com','123456','Khalil', 'Kouiss',2),
  ('hbenderouach@sqli.com','123456','Hamza', 'Benderouach',4),
  ('obellouki@sqli.com','123456','Outhmane', 'Bellouki',1),
  ('mbeilil@sqli.com','123456','Mohammed', 'Beilil',3);

  CREATE TABLE authorization (
  authorization_id INT AUTO_INCREMENT  PRIMARY KEY,
  authorization_title VARCHAR(250) NOT NULL,
  authorization_description VARCHAR(250) NOT NULL,
  authorization_url VARCHAR(250) NOT NULL,
  role_id INT NOT NULL
);

    INSERT INTO authorization (authorization_title, authorization_description,authorization_url,role_id) VALUES
      ('Find all answer', 'Find all roles', '/qcm-app-v1/answer/all',2),
      ('Find all choice', 'Find all roles', '/qcm-app-v1/choice/all',2),
      ('Find all question', 'Find all roles', '/qcm-app-v1/question/all',2);


