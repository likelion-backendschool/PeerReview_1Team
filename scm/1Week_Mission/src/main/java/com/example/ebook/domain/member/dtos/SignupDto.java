package com.example.ebook.domain.member.dtos;

import com.example.ebook.domain.member.entities.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class SignupDto {
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String username;
    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;
    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String passwordChk;

    private String nickname;

    public Member toEntity(){
        return Member.builder()
                .username(username)
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
