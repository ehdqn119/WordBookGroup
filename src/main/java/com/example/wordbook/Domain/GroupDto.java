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
    @NotBlank(message = "제목이 비어있습니다")
    private String title;
    @NotBlank(message = "작가가 비어있습니다")
    private String author;
    @Min(value = 0, message = "가격이 비어있습니다")
    private int price;


}
