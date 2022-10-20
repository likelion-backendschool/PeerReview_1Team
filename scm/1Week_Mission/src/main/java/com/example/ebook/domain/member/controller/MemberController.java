package com.example.ebook.domain.member.controller;

import com.example.ebook.auth.dto.MemberContext;
import com.example.ebook.domain.member.dtos.FindUsernameDto;
import com.example.ebook.domain.member.dtos.ModifyMemberDto;
import com.example.ebook.domain.member.dtos.SignupDto;
import com.example.ebook.domain.member.entities.Member;
import com.example.ebook.domain.member.service.MemberService;
import com.example.ebook.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("member")
public class MemberController {
    private final MemberService memberService;
    private final MailService mailService;
    @GetMapping("join")
    public String signup(SignupDto signupDto){
        return "member/signup_form";
    }

    @PostMapping("join")
    public String signup(
            @Valid SignupDto signupDto,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()) {
            return "member/signup_form";
        }

        if (!signupDto.getPassword().equals(signupDto.getPasswordChk())) {
            bindingResult.rejectValue("passwordChk", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "member/signup_form";
        }
        Member member = null;
        try{
             member = memberService.signup(signupDto);
        } catch(DataIntegrityViolationException e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            bindingResult.rejectValue("username", "usernameDuplicate",
                    "회원 id 혹은 email 값이 중복됩니다.");
            return "member/signup_form";
        }
        List<String> emails = new ArrayList<>();
        emails.add(member.getEmail());
        mailService.sendMail(emails ,"회원가입을 축하합니다", "ebook에 가입하신 것을 환영합니다!");

        return "redirect:/";
    }

    @GetMapping("login")
    public String login(){
        return "member/login_form";
    }

    @GetMapping("findUsername")
    public String findUsername(
            FindUsernameDto findUsernameDto
    ){
        return "member/find_username_form";
    }

    @PostMapping("findUsername")
    public String findUsername(
           @Valid FindUsernameDto findUsernameDto,
           BindingResult bindingResult,
           Model model
    ){
        if (bindingResult.hasErrors()) {
            return "member/find_username_form";
        }
        String username;

        try{
            username = memberService.findUsername(findUsernameDto.getEmail());
        } catch (RuntimeException e){
            bindingResult.rejectValue("email", "emailInCorrect",
                    e.getMessage());
            return "member/find_username_form";
        }

        model.addAttribute("username", username);
        return "member/show_username";
    }

    @GetMapping("profile")
    public String memberProfile(
            @AuthenticationPrincipal MemberContext context,
            Model model
    ){
        Member member = memberService.getMemberById(context.getId());

        model.addAttribute("member", member);

        return "member/profile";
    }

    @GetMapping("modify")
    public String modifyMember(
            @AuthenticationPrincipal MemberContext context,
            Model model
    ){
        Member member = memberService.getMemberById(context.getId());

        ModifyMemberDto modifyMemberDto = new ModifyMemberDto(member);

        model.addAttribute("member", member);
        model.addAttribute("modifyMemberDto",modifyMemberDto);

        return "member/modify_form";
    }

    @PostMapping("modify")
    public String modifyMember(
            Model model,
            @Valid ModifyMemberDto modifyMemberDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal MemberContext context
    ){
        if (bindingResult.hasErrors()) {
            Member member = memberService.getMemberById(context.getId());
            model.addAttribute("modifyMemberDto",modifyMemberDto);
            model.addAttribute("member", member);
            return "member/modify_form";
        }

        memberService.updateMember(context.getId(), modifyMemberDto);

        return "redirect:/member/profile";
    }
}
