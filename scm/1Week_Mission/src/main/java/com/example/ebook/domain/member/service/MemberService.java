package com.example.ebook.domain.member.service;

import com.example.ebook.domain.member.dtos.ModifyMemberDto;
import com.example.ebook.domain.member.dtos.SignupDto;
import com.example.ebook.domain.member.entities.Member;
import com.example.ebook.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member signup(SignupDto signupDto) throws DataIntegrityViolationException {
        Member member = signupDto.toEntity();

        member.changePassword(passwordEncoder.encode(member.getPassword()));

        memberRepository.save(member);

        return member;
    }

    public String findUsername(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("해당 이메일을 가진 멤버가 존재하지 않습니다."));

        return member.getUsername();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("맴버가 존재하지 않습니다."));
    }

    @Transactional
    public void updateMember(Long memberId, ModifyMemberDto modifyMemberDto) {
        Member member =  memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("맴버가 존재하지 않습니다."));

        // modify
        member.changeEmail(modifyMemberDto.getEmail());
        member.changeNickname(modifyMemberDto.getNickname());
    }
}
