package com.example.ebook.domain.post.cotroller;


import com.example.ebook.auth.dto.MemberContext;
import com.example.ebook.domain.member.entities.Member;
import com.example.ebook.domain.member.service.MemberService;
import com.example.ebook.domain.post.dtos.CreatePostDto;
import com.example.ebook.domain.post.entities.Post;
import com.example.ebook.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("post")
public class PostController {
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("list")
    public String findPostList(
            Model model
    ){
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "post/list";
    }

    @GetMapping("write")
    public String createPost(
            Model model,
            CreatePostDto createPostDto
    ){

        return "post/create_form";
    }

    @PostMapping("write")
    public String createPost(
            Model model,
            @Valid CreatePostDto createPostDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal MemberContext context
    ){
        if (bindingResult.hasErrors()) {
            return "post/create_form";
        }
        Member member = memberService.getMemberById(context.getId());

        Post post = postService.createPost(createPostDto, member);

        return "redirect:/post/list";
    }

    @GetMapping("{id}")
    public String findOnePost(
            Model model,
            @PathVariable Long id
    ){
        Post post = postService.findOnePost(id);
        model.addAttribute("post", post);
        return "post/detail";
    }
}
