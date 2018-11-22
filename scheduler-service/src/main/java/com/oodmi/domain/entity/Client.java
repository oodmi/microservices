package com.oodmi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "client_login")
    private String login;

    @Column(name = "time_key")
    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();

    @Column(name = "client_name")
    private String name;

    @Column(name = "client_surname")
    private String surname;

    @Column(name = "client_email")
    private String email;

    @Column(name = "client_password")
    private String password;

    @Column(name = "client_birthday")
    private LocalDate birthday;

    @OneToOne
    @JoinColumn(name = "vk_id")
    private Vk vk;
}
