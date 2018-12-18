package com.oodmi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "vk_friend_history")
public class VkFriendHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vk_friend_history_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vk_id")
    private Vk vk;

    @Column(name = "vk_friend_history_count")
    private Long count;

    @Column(name = "vk_friend_history_content")
    private String content;

    @Column(name = "vk_friend_history_uuid")
    private String uuid;

    @Column(name = "time_key")
    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();

}
