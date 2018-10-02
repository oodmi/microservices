CREATE TABLE message (
  message_id      BIGSERIAL PRIMARY KEY NOT NULL,
  message_content VARCHAR(255)          NULL,
  client_id       BIGINT                NOT NULL
);

ALTER TABLE message
  ADD FOREIGN KEY (client_id) REFERENCES client (client_id);