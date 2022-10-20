package com.example.ebook.domain.post.service;

import com.example.ebook.domain.member.entities.Member;
import com.example.ebook.domain.post.dtos.CreatePostDto;
import com.example.ebook.domain.post.entities.Post;
import com.example.ebook.domain.post.entities.PostHashTag;
import com.example.ebook.domain.post.entities.PostKeyword;
import com.example.ebook.domain.post.repository.PostHashTagRepository;
import com.example.ebook.domain.post.repository.PostKeywordRepository;
import com.example.ebook.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostKeywordRepository postKeywordRepository;
    private final PostHashTagRepository postHashTagRepository;

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public Post findOnePost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시판을 찾을 수 없습니다."));
    }

    @Transactional
    public Post createPost(CreatePostDto createPostDto, Member member) {
        String[] postKeywordContents =  createPostDto.getPostKeywordContent().split("#");
        List<PostKeyword> postKeywords = (List<PostKeyword>) Arrays.stream(postKeywordContents).map(
                        content ->
                                PostKeyword.builder()
                                        .content(content.trim())
                                        .build()
                ).toList();

        List<PostKeyword> savePostKeywords = postKeywordRepository.saveAll(postKeywords);

        Post post = createPostDto.toEntity();
        Post savePost = postRepository.save(post);

        List<PostHashTag> postHashTags = new ArrayList<>();
        for(PostKeyword postKeyword : savePostKeywords){
            postHashTags.add(
                    PostHashTag.builder()
                            .post(savePost)
                            .postKeyword(postKeyword)
                            .member(member)
                            .build()
            );
        }

        postHashTagRepository.saveAll(postHashTags);

        return post;
    }
}
