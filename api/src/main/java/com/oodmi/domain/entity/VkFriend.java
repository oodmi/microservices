package com.oodmi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "vk_friend")
public class VkFriend {

    @Id
    @Column(name = "vk_friend_id")
    private Long id;

    @Column(name = "vk_friend_name")
    private String name;

    @Column(name = "vk_friend_surname")
    private String surname;

    @Column(name = "vk_friend_email")
    private String email;

    @Column(name = "vk_friend_domain")
    private String domain;

    @Column(name = "vk_friend_photo")
    private String photo;

    @Column(name = "time_key")
    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();
}
