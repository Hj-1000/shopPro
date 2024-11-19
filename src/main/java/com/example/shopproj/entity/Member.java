package com.example.shopproj.entity;

import com.example.shopproj.constant.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString @NoArgsConstructor @AllArgsConstructor
@Builder
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String address;

    // 권한 2개

    @Enumerated(EnumType.STRING)
    private Role role;
}
