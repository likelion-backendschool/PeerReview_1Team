package com.example.ebook.domain.member.dtos;

import com.example.ebook.domain.member.entities.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyMemberDto {
    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    private String nickname;

    public ModifyMemberDto(Member member){
        email = member.getEmail();
        nickname = member.getNickname();
    }
}
