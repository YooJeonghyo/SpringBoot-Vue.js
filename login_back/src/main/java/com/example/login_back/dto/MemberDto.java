package com.example.login_back.dto;

import com.example.login_back.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String memberid;
    private String password;
    private String membername;
    private String email;
    private String phone;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Member toEntity() {
        Member build = Member.builder()
                .id(id)
                .memberid(memberid)
                .password(password)
                .membername(membername)
                .phone(phone)
                .email(email)
                .createdDate(createdDate)
                .build();
        return build;
    }

    @Builder
    public MemberDto(Long id, String memberid, String password, String membername, String email, String phone, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.memberid = memberid;
        this.password = password;
        this.membername = membername;
        this.phone = phone;
        this.email = email;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
