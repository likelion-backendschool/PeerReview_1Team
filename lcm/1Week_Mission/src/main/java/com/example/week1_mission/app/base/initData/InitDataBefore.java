package com.example.week1_mission.app.base.initData;

import com.example.week1_mission.app.member.entity.Member;
import com.example.week1_mission.app.member.service.MemberService;
import com.example.week1_mission.app.post.entity.Post;
import com.example.week1_mission.app.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface InitDataBefore {

    default void before(MemberService memberService, PostService postService) {

        Member member1 = memberService.join("user1","1234","user1@test.com","사용자1");
        Member member2 = memberService.join("user2", "1234", "user2@test.com", "사용자2");

        Post post1 = postService.write(member1,"제목1","내용1");
        Post post2 = postService.write(member1,"제목2","내용2");
        Post post3 = postService.write(member2,"제목3","내용3");
        Post post4 = postService.write(member2,"제목4","내용4");
    }
}
