package com.example.week1_mission.app.member.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ModifyForm {


    @NotEmpty
    private String email;
    private String nickname;
}
