CREATE TABLE vk_friend_history (
  vk_friend_history_id      BIGSERIAL PRIMARY KEY NOT NULL,
  vk_id                     BIGINT                NULL,
  vk_friend_history_content TEXT                  NOT NULL,
  vk_friend_history_uuid    VARCHAR(255)          NOT NULL,
  vk_friend_history_count   BIGINT                NOT NULL DEFAULT 0,
  time_key                  TIMESTAMP             NOT NULL DEFAULT current_timestamp
);

ALTER TABLE vk_friend_history
  ADD FOREIGN KEY (vk_id) REFERENCES vk (vk_id);