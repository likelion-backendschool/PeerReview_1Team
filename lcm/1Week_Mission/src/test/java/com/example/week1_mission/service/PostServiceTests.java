package com.example.week1_mission.service;

import com.example.week1_mission.app.member.entity.Member;
import com.example.week1_mission.app.member.repository.MemberRepository;
import com.example.week1_mission.app.post.entity.Post;
import com.example.week1_mission.app.post.form.PostForm;
import com.example.week1_mission.app.post.repository.PostRepository;
import com.example.week1_mission.app.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class PostServiceTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;

    @Test
    @DisplayName("글 생성")
    void t1() {

        Member author = memberRepository.findByUsername("user1").orElse(null);

        Post post = postService.write(author, "제목1","내용1");

        assertThat(post).isNotNull();
        assertThat(post.getAuthor().getUsername()).isEqualTo("user1");
        assertThat(post.getSubject()).isEqualTo("제목1");
        assertThat(post.getContent()).isEqualTo("내용1");
    }

    @Test
    @DisplayName("글 수정")
    void t2(){
        Post post = postService.findById(1L).get();

        postService.modify(post, "제목 new", "내용 new");

        assertThat(post).isNotNull();
        assertThat(post.getSubject()).isEqualTo("제목 new");
        assertThat(post.getContent()).isEqualTo("내용 new");
    }
}
