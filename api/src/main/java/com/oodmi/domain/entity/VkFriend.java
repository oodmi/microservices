package com.oodmi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vk_friend_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vk_id")
    private Vk vk;

    @Column(name = "vk_friend_count")
    private Long count;

    @Column(name = "vk_friend_content")
    private String content;

    @Column(name = "vk_friend_uuid")
    private String uuid;

    @Column(name = "time_key")
    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();

}
