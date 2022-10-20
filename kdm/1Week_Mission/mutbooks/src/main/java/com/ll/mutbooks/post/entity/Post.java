package com.ll.mutbooks.post.entity;

import com.ll.mutbooks.common.entity.BaseEntity;
import com.ll.mutbooks.member.entity.Member;
import com.ll.mutbooks.post.dto.PostWriteFormDto;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "post_subject")
    private String subject;

    @Column(name = "post_content")
    private String content;

    @Column(name = "post_content_html")
    private String contentHTML;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public static Post createPost(PostWriteFormDto postWriteFormDto, Member member) {
        return Post.builder()
                .subject(postWriteFormDto.getSubject())
                .content(postWriteFormDto.getContent())
                .contentHTML(postWriteFormDto.getContentHTML())
                .member(member)
                .build();
    }
}
