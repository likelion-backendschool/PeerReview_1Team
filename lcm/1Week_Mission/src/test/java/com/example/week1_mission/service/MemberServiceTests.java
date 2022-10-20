package com.example.week1_mission.service;

import com.example.week1_mission.app.member.entity.Member;
import com.example.week1_mission.app.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class MemberServiceTests {

    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입")
    void join() {
        String username = "user5";
        String password = "1234";
        String email = "user52test.com";
        String nickname = "사용자5";

        memberService.join(username, password, email, nickname);

        Member member = memberService.findByUsername("user5").get();
        assertThat(member.getUsername()).isNotNull();
        assertThat(member.getEmail()).isNotNull();
        assertThat(passwordEncoder.matches(password, member.getPassword())).isTrue();
    }
}
