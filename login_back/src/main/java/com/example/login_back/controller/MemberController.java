package com.example.login_back.controller;

import com.example.login_back.dto.LoginDto;
import com.example.login_back.dto.MemberDto;
import com.example.login_back.entity.Member;
import com.example.login_back.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/members")
    public List getAll() {
        List<MemberDto> memberDtoList = memberService.getList();
        return memberDtoList;
    }

    @PostMapping("/signup")
    public Long signup(@RequestBody MemberDto memberDto) {
        return memberService.save(memberDto);
    }

    @PostMapping("/signup/idCheck")
    public ResponseEntity<Boolean> idCheck(@RequestBody String memberid) {
        return ResponseEntity.ok(memberService.idCheck(memberid));
    }

    @PostMapping("/login")
    public Member login(@RequestBody LoginDto loginDto) {
        //log.info("넘어온 데이터 : {}", loginDto);
        return memberService.doLogin(loginDto);
    }

    @GetMapping("/info/{id}")
    public MemberDto info(@PathVariable("id") Long id) {
        MemberDto memberDto = memberService.getInfo(id);
        return memberDto;
    }

    @PutMapping("/correction")
    public Long correction(@RequestBody MemberDto memberDto) {
        log.info("넘어온 데이터 : {}", memberDto);
        return memberService.save(memberDto);

    }

    @DeleteMapping("/correction/{id}")
    public void deleteMember(@PathVariable("id") Long id) {
        log.info("넘어온 데이터 : {}", id);
        memberService.deleteMember(id);
    }
}
