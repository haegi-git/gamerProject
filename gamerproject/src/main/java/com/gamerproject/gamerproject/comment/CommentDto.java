package com.gamerproject.gamerproject.comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    @NotEmpty(message = "아무것도 작성되지 않았습니다.")
    @Size(min = 5)
    private String comment;
}
