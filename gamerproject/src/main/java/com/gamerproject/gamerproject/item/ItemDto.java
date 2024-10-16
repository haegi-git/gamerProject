package com.gamerproject.gamerproject.item;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {

    @NotEmpty
    @Size(min = 3, max = 20)
    private String title;

    @NotEmpty
    @Size(min = 10, max=2500)
    private String contents;


}
