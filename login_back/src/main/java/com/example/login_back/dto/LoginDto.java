package com.example.login_back.dto;
import com.example.login_back.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginDto {
    private String memberId;
    private String password;

    @Builder
    public LoginDto(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}
