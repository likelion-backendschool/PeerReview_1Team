package com.example.week1_mission.app.member.entity;

import com.example.week1_mission.app.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Member extends BaseEntity {

    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private int authLevel; // 회원 권한 레벨
    private String email;
    private String nickname;
    @CreatedDate
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public Member(long id) {
        super(id);
    }

    public String getName() {
        return username;
    }
}
