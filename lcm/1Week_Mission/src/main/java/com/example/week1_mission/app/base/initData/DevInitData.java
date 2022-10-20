package com.example.week1_mission.app.base.initData;

import com.example.week1_mission.app.member.service.MemberService;
import com.example.week1_mission.app.post.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevInitData implements InitDataBefore{

    @Bean
    CommandLineRunner initData(MemberService memberService, PostService postService) {
        return args -> {
            before(memberService, postService);
        };
    }
}
