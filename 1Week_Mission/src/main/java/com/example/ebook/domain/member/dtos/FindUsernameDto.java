package com.example.ebook.domain.member.dtos;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class FindUsernameDto {
    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;
}
