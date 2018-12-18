ALTER TABLE IF EXISTS vk_friend
  RENAME TO vk_friend_history;

ALTER TABLE vk_friend_history
  RENAME COLUMN vk_friend_id TO vk_friend_history_id;

ALTER TABLE vk_friend_history
  RENAME COLUMN vk_friend_content TO vk_friend_history_content;

ALTER TABLE vk_friend_history
  RENAME COLUMN vk_friend_uuid TO vk_friend_history_uuid;

ALTER TABLE vk_friend_history
  RENAME COLUMN vk_friend_count TO vk_friend_history_count;

CREATE TABLE vk_friend (
  vk_friend_id      BIGINT PRIMARY KEY NOT NULL,
  vk_friend_name    VARCHAR(255)       NULL,
  vk_friend_surname VARCHAR(255)       NULL,
  vk_friend_email   VARCHAR(255)       NULL,
  vk_friend_domain  VARCHAR(255)       NULL,
  vk_friend_photo   VARCHAR(255)       NULL,
  time_key          TIMESTAMP          NOT NULL DEFAULT current_timestamp
);