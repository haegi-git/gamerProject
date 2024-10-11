package com.gamerproject.gamerproject.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    @NotEmpty(message = "아이디는 필수입니다.")
    @Size(min = 6, max = 12)
    private String userid;

    @NotEmpty
    @Size(min = 6, max = 12)
    private String password;


    @NotEmpty
    @Email(message = "이메일 형식이 맞지않습니다.")
    private String email;

    @NotEmpty
    @Size(min = 1, max = 8)
    private String displayName;

}
