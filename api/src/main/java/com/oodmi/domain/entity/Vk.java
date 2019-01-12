package com.oodmi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "vk")
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

}
