package com.example.week1_mission.app.member.service;

import com.example.week1_mission.app.member.entity.Member;
import com.example.week1_mission.app.member.exception.AlreadyJoinException;
import com.example.week1_mission.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member join(String username, String password, String email, String nickname) {


        // 회원 중복 체크
        if (memberRepository.findByUsername(username).isPresent()) {
            throw new AlreadyJoinException();
        }

        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .nickname(nickname)
                .build();

        memberRepository.save(member);

        return member;
    }

    // 테스트용
    public Optional<Member> findByUsername(String username) {

        return memberRepository.findByUsername(username);
    }

    public Member findByEmail(String email) {

        return memberRepository.findByEmail(email);
    }

    public Optional<Member> findById(Long id) {
        
        return memberRepository.findById(id);
    }

    @Transactional
    public void modify(Member member, String nickname, String email) {

        member.setNickname(nickname);
        member.setEmail(email);

        memberRepository.save(member);
    }

    public Member getMemberByUsername(String username) {
        return memberRepository.findByUsername(username).orElse(null);
    }

    public Optional<Member> findForPrintById(Long id) {
        Optional<Member> opMember = findById(id);

        if (opMember.isEmpty()) return opMember;

        return opMember;
    }
}

