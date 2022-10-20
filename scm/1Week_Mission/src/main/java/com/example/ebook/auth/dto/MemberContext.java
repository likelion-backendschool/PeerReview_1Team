package com.example.ebook.auth.dto;

import com.example.ebook.domain.member.entities.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class MemberContext extends User {
    private final Long id;

    public MemberContext(Member member, List<GrantedAuthority> authorities){
        super(member.getUsername(), member.getPassword(), authorities);
        id = member.getId();
    }
}
