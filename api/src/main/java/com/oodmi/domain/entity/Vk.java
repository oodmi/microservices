package com.oodmi.domain.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "vk")
@ToString(exclude = "friendsHistory")
public class Vk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vk_id")
    private Long id;

    @Column(name = "vk_user_id")
    private String userId;

    @Column(name = "vk_token")
    private String token;

    @Column(name = "vk_valid")
    @Builder.Default
    private Boolean valid = Boolean.TRUE;

    @OneToMany(mappedBy = "vk", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<VkFriendHistory> friendsHistory;
}
