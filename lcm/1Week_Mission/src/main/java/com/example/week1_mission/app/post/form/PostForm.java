package com.example.week1_mission.app.post.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class PostForm {

    @NotEmpty
    private String subject;
    @NotEmpty
    private String content;
//  private String keyword; // PostKeyword 해시태그

}
