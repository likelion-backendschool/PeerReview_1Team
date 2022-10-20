package com.example.week1_mission.app.member.controller;

import com.example.week1_mission.app.member.entity.Member;
import com.example.week1_mission.app.member.form.JoinForm;
import com.example.week1_mission.app.member.form.ModifyForm;
import com.example.week1_mission.app.member.service.MemberService;
import com.example.week1_mission.app.security.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    // 회원 가입
    @PreAuthorize("isAnonymous()")
    @GetMapping("/join")
    public String showJoin(){

        return "member/join";
    }

    // 회원 가입 폼
    @PreAuthorize("isAnonymous()")
    @PostMapping("/join")
    public String join(HttpServletRequest req, @Valid JoinForm joinForm){

        Member oldMember = memberService.getMemberByUsername(joinForm.getUsername());

        if (oldMember != null) {
            return "redirect:/?errorMsg=Already done.";
        }

        memberService.join(joinForm.getUsername(), joinForm.getPassword(), joinForm.getEmail(), joinForm.getNickname());

        return "redirect:/member/login";
    }

    // 로그인
    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String showLogin(HttpServletRequest request) {
        String uri = request.getHeader("Referer");

        if (uri != null && !uri.contains("/member/login")) {
            request.getSession().setAttribute("prevPage", uri);
        }

        return "member/login";
    }

    // 아이디 찾기
    @PreAuthorize("isAnonymous()")
    @GetMapping("/findUsername")
    public String showFindUsername(){

        return "member/findUsername";
    }

    // 아이디 찾기
    @PreAuthorize("isAnonymous()")
    @PostMapping("/findUsername")
    public String doFindUsername(@Valid String email, Model model) {
        // 메일로 아이디 찾기
        Member findMember = memberService.findByEmail(email);

        model.addAttribute("findMember", findMember);

        return "member/foundUsername";
    }

    // 회원 정보 수정 폼
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify")
    public String showModify(@AuthenticationPrincipal MemberDto memberDto, Model model){

        Member member = memberDto.getMember();
        model.addAttribute("member", member);

        return "member/modify";
    }

    // 회원 정보 수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify")
    public String doModify(@AuthenticationPrincipal MemberDto memberDto, @Valid ModifyForm modifyForm){

        Member member = memberService.findForPrintById(memberDto.getId()).get();

        memberService.modify(member, modifyForm.getNickname(), modifyForm.getEmail());

        // return "redirect:/member/profile"; 프로필 구현 후 수정 예정
        return "redirect:/";
    }

    // 프로필
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String showProfile(){

        return "member/profile";
    }
}
