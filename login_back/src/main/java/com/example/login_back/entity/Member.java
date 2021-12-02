package com.example.login_back.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memberid;

    @Column
    private String password;

    @Column
    private String membername;

    @Column
    private String phone;

    @Column
    private String email;

    @CreatedDate
    @Column
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Builder
    public Member(Long id, String memberid, String password, String membername, String phone, String email, LocalDateTime createdDate) {
        this.id = id;
        this.memberid = memberid;
        this.password = password;
        this.membername = membername;
        this.phone = phone;
        this.email = email;
        this.createdDate = createdDate;
    }
}
