CREATE TABLE vk_friend (
  vk_friend_id      BIGINT PRIMARY KEY NOT NULL,
  vk_friend_name    VARCHAR(255)       NULL,
  vk_friend_surname VARCHAR(255)       NULL,
  vk_friend_email   VARCHAR(255)       NULL,
  vk_friend_domain  VARCHAR(255)       NULL,
  vk_friend_photo   VARCHAR(255)       NULL,
  time_key          TIMESTAMP          NOT NULL DEFAULT current_timestamp
);