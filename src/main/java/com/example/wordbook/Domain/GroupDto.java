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

    @Min(value = 1,message = "유저의 값은 0이상 이어야 합니다")
    private Long id;
    @NotBlank(message = "제목이 비어있습니다")
    private String title;
    @NotBlank(message = "작가가 비어있습니다")
    private String author;
    @Min(value = 0, message = "가격이 비어있습니다")
    private int price;


}
