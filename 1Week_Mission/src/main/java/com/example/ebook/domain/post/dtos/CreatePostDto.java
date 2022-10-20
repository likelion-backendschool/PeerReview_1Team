package com.example.ebook.domain.post.dtos;

import com.example.ebook.domain.post.entities.Post;
import com.example.ebook.domain.post.entities.PostKeyword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class CreatePostDto {
    @NotEmpty(message = "제목은 필수항목입니다.")
    private String subject;
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
    private String contentHtml;
    private String postKeywordContent;

    public Post toEntity(){
        return Post.builder()
                .subject(subject)
                .content(content)
                .contentHtml(contentHtml)
                .build();
    }
}
