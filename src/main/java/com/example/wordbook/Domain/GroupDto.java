package com.example.wordbook.Domain;


import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GroupDto {

    @NotBlank(message = "키값이 비어있습니다.")
    private String key;

    @NotBlank(message = "이름값이 비어 있습니다.")
    private String name;


}
