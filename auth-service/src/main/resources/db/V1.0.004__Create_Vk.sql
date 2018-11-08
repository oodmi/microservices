CREATE TABLE vk (
  vk_id      BIGSERIAL PRIMARY KEY NOT NULL,
  vk_user_id VARCHAR(255)          NULL,
  vk_token   VARCHAR(255)          NOT NULL,
  vk_valid   BOOL                  NOT NULL DEFAULT TRUE
);

ALTER TABLE client
  ADD COLUMN vk_id BIGINT NULL;

ALTER TABLE client
  ADD FOREIGN KEY (vk_id) REFERENCES vk (vk_id);