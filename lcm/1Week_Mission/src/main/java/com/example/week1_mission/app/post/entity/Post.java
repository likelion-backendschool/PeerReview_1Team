package com.example.week1_mission.app.post.entity;

import com.example.week1_mission.app.base.entity.BaseEntity;
import com.example.week1_mission.app.member.entity.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.*;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
public class Post extends BaseEntity {

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content; // 마크다운 원문 내용
    private String contentHtml; // html으로 변환된 내용

    @ManyToOne(fetch = LAZY)
    private Member author; // 글쓴이
}
