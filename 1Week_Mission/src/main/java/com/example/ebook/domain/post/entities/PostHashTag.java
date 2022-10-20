package com.example.ebook.domain.post.entities;

import com.example.ebook.domain.member.entities.Member;
import com.example.ebook.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@SuperBuilder
@Entity
@Table(indexes = @Index(name="unique_post_postkeyword", columnList = "post_id, post_keyword_id", unique = true))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostHashTag extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private PostKeyword postKeyword;
}
