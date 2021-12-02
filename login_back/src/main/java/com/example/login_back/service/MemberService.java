package com.example.login_back.service;

import com.example.login_back.dto.LoginDto;
import com.example.login_back.dto.MemberDto;
import com.example.login_back.entity.Member;
import com.example.login_back.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService{

    private MemberRepository memberRepository;

    public List<MemberDto> getList() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();

        for(Member member: memberList) {
            MemberDto memberDto = MemberDto.builder()
                    .id(member.getId())
                    .memberid(member.getMemberid())
                    .membername(member.getMembername())
                    .email(member.getEmail())
                    .phone(member.getPhone())
                    .createdDate(member.getCreatedDate())
                    .build();
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }

    @Transactional
    public Long save(MemberDto memberDto) {
        return memberRepository.save(memberDto.toEntity()).getId();
    }

    public boolean idCheck(String id) {
        return memberRepository.existsByMemberid(id);
    }

    public Member doLogin(LoginDto loginDto) {
        Member member = this.memberRepository.findMember(loginDto.getMemberId(), loginDto.getPassword());
        return member;
    }

    @Transactional
    public MemberDto getInfo(Long id) {
        Member member = memberRepository.findById(id).get();

        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .memberid(member.getMemberid())
                .membername(member.getMembername())
                .email(member.getEmail())
                .phone(member.getPhone())
                .createdDate(member.getCreatedDate())
                .modifiedDate(member.getModifiedDate())
                .password((member.getPassword()))
                .build();
        return memberDto;
    }

    @Transactional
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

}
