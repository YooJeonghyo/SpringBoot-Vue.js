package com.example.login_back.repository;

import com.example.login_back.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByMemberid(String memberid);

    @Query("select m from Member m where memberId = :memberid and password = :password")
    Member findMember(@Param("memberid") String memberid, @Param("password") String password);

   // Optional<Member> findByMemberidAndPassword(String memberid, String password);
}
