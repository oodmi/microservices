CREATE TABLE vk_friend (
  vk_friend_id      BIGSERIAL PRIMARY KEY NOT NULL,
  vk_id             BIGINT                NULL,
  vk_friend_content TEXT                  NOT NULL,
  vk_friend_uuid    VARCHAR(255)          NOT NULL,
  time_key          TIMESTAMP             NOT NULL DEFAULT current_timestamp
);

ALTER TABLE vk_friend
  ADD FOREIGN KEY (vk_id) REFERENCES vk (vk_id);