ALTER TABLE client
  ADD COLUMN client_name VARCHAR(255) NULL;

ALTER TABLE client
  ADD COLUMN client_surname VARCHAR(255) NULL;

ALTER TABLE client
  ADD COLUMN client_email VARCHAR(255) NULL;

ALTER TABLE client
  ADD COLUMN client_password VARCHAR(255) NULL;

ALTER TABLE client
  ADD COLUMN client_birthday TIMESTAMP NULL;