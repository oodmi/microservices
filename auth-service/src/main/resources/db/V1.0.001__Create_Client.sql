CREATE TABLE role (
  role_id   BIGSERIAL PRIMARY KEY NOT NULL,
  role_name VARCHAR(255)          NOT NULL DEFAULT 'USER'
);

CREATE TABLE client (
  client_id    BIGSERIAL PRIMARY KEY NOT NULL,
  client_login VARCHAR(255)          NULL,
  role_id      BIGINT                NOT NULL,
  time_key     TIMESTAMP             NOT NULL DEFAULT current_timestamp
);

ALTER TABLE client
  ADD FOREIGN KEY (role_id) REFERENCES role (role_id);