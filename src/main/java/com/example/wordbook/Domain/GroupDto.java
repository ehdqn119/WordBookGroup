package com.example.wordbook.Domain;


import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GroupDto {


    private Long id;
    @NotBlank(message = "그룹이름이 비어있습니다.")
    private String group_name;
    @NotBlank(message = "그룹권한이 비어있습니다.")
    private String group_rule;

}
