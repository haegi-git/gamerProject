package com.gamerproject.gamerproject.member;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginDto {

    @NotEmpty(message = "아이디는 필수입니다.")
    @Size(min = 6, max = 12)
    private String userid;

    @NotEmpty
    @Size(min = 6, max = 12)
    private String password;

}
