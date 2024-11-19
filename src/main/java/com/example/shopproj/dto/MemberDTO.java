package com.example.shopproj.dto;

import com.example.shopproj.constant.Role;
import com.example.shopproj.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 10, message = "이름은 2~10글자 사이로 작성해주세요.")
    private String name;

    @Email(message = "이메일은 형식으로 작성해주세요")
    @NotBlank
    @Size(min = 2, max = 20, message = "이메일은 2~20자로 작성해주세요")
    private String email;

    @NotBlank
    @Size(min = 4, max = 10)
    private String password;

    private String address;

    private Role role;

    public Member dtoToEntity(MemberDTO memberDTO){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setAddress(memberDTO.getAddress());
        member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        member.setRole(Role.ADMIN);

        return member;
    }
}
