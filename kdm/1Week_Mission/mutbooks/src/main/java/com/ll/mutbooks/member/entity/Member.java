package com.ll.mutbooks.member.entity;

import com.ll.mutbooks.common.entity.BaseEntity;
import com.ll.mutbooks.member.dto.MemberJoinFormDto;
import com.ll.mutbooks.member.dto.MemberModifyFormDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_username", unique = true)
    private String username;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_nickname", unique = true)
    private String nickname;

    @Column(name = "member_email", unique = true)
    private String email;

    @Column(name = "member_auth_level")
    @Enumerated(EnumType.STRING)
    private MemberRole authLevel;

    @Builder
    public Member(String username, String password, String nickname, String email, MemberRole authLevel) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.authLevel = authLevel;
    }

    public void change(MemberModifyFormDto memberModifyFormDto) {
        this.email = memberModifyFormDto.getEmail();
        this.nickname = memberModifyFormDto.getNickname();
    }

    public static Member createMember(MemberJoinFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .username(memberFormDto.getUsername())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))
                .nickname(memberFormDto.getNickname())
                .email(memberFormDto.getEmail())
                .authLevel(MemberRole.USER)
                .build();
    }

}
