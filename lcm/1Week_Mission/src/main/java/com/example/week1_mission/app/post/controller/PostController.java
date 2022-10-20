package com.example.week1_mission.app.post.controller;

import com.example.week1_mission.app.member.entity.Member;
import com.example.week1_mission.app.post.entity.Post;
import com.example.week1_mission.app.post.exception.AuthorCanNotModifyException;
import com.example.week1_mission.app.post.exception.AuthorCanNotSeeException;
import com.example.week1_mission.app.post.form.PostForm;
import com.example.week1_mission.app.post.service.PostService;
import com.example.week1_mission.app.security.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    // 글 작성 폼
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/write")
    public String showWrite(){

        return "post/write";
    }

    // 글 작성
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write")
    public String write(@AuthenticationPrincipal MemberDto memberDto, @Valid PostForm postForm){

        Member author = memberDto.getMember();

        Post post = postService.write(author, postForm.getSubject(), postForm.getContent());

        return "redirect:/post/%d".formatted(post.getId());
    }

    // 글 상세 페이지
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public String detail(@AuthenticationPrincipal MemberDto memberContext, @PathVariable Long id, Model model){

        Post post = postService.findForPrintById(id).get();

        Member author = memberContext.getMember();

        if (postService.authorCanModify(author, post) == false) {
            throw new AuthorCanNotSeeException();
        }

        model.addAttribute("post", post);

        return "post/detail";
    }

    // 글 수정 폼
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/modify")
    public String showModify(@AuthenticationPrincipal MemberDto memberDto, @PathVariable long id, Model model){
        Post post = postService.findForPrintById(id).get();

        Member author = memberDto.getMember();

        if(postService.authorCanModify(author, post) == false){
            throw new AuthorCanNotModifyException();

        }
        model.addAttribute("post", post);

        return "post/modify";
    }

    // 글 수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/modify")
    public String modify(@AuthenticationPrincipal MemberDto memberDto, @PathVariable long id, @Valid PostForm postForm){
        Post post = postService.findById(id).get();

        Member author = memberDto.getMember();

        if(postService.authorCanModify(author, post) == false){
            throw new AuthorCanNotModifyException();
        }

        postService.modify(post, postForm.getSubject(), postForm.getContent());
        return "redirect:/post/%d".formatted(post.getId());
    }

    // 글 리스트
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/list")
    public String showList(@AuthenticationPrincipal MemberDto memberDto, Model model){

        Member author = memberDto.getMember();

        List<Post> postList = postService.findAllByAuthorId(author.getId());

        model.addAttribute("postList", postList);

        return "post/list";
    }

    // 글 삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/delete")
    public String postDelete(@AuthenticationPrincipal MemberDto memberDto,  @PathVariable long id){

        Member author = memberDto.getMember();

        Post post = postService.findById(id).get();

        if (postService.authorCanDelete(author, post) == false) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }

        postService.delete(post);

        return "redirect:/post/list";
    }

}
