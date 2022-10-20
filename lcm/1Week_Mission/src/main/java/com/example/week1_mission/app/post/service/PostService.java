package com.example.week1_mission.app.post.service;

import com.example.week1_mission.app.member.entity.Member;
import com.example.week1_mission.app.post.entity.Post;
import com.example.week1_mission.app.post.form.PostForm;
import com.example.week1_mission.app.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post write(Member author,  String subject, String content) {
        Post post = Post.builder()
                .author(author)
                .subject(subject)
                .content(content)
                .build();

        postRepository.save(post);

        return post;
    }

    public Optional<Post> findById(Long postId) {
        return postRepository.findById(postId);
    }

    @Transactional
    public void modify(Post post, String subject, String content) {

        post.setSubject(subject);
        post.setContent(content);
    }

    public Optional<Post> findForPrintById(Long id) {
        Optional<Post> opPost = findById(id);

        if (opPost.isEmpty()) return opPost;

        return opPost;
    }

    public boolean authorCanModify(Member author, Post post) {

        return author.getId().equals(post.getAuthor().getId());
    }

    public List<Post> findAllByAuthorId(Long id) {
        return postRepository.findAllByAuthorId(id);
    }

    @Transactional
    public void delete(Post post) {
        postRepository.delete(post);
    }

    public boolean authorCanDelete(Member author, Post post) {

        return author.getId().equals(post.getAuthor().getId());
    }
}
