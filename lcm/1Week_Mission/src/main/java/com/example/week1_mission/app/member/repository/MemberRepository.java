package com.example.week1_mission.app.member.repository;

import com.example.week1_mission.app.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    Member findByEmail(String email);
}
