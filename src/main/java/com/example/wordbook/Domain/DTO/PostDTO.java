package com.example.wordbook.Domain.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {

    @Schema(description = "포스트의 제목입니다.", defaultValue = "경주여행 1일차")
    private String title;

    @Schema(description = "해당 포스트 날짜의 온도입니다.", defaultValue = "36.5")
    private String temperature;

    @Schema(description = "해당 포스트 날짜의 날씨입니다.", defaultValue = "구름,비")
    private String weather;

    @Schema(description = "해당 포스트의 당일 날짜입니다.", defaultValue = "2021.06.05")
    private String day;


    // 애는 왜 있어야 되는 줄 잘 모르겠음 //
    /*@Schema(description = "여행 총 날짜입니다.", defaultValue = "여행을 가다.")
    private String tripDate;
*/
    @Schema(description = "해당 포스트의 내용입니다.", defaultValue = "여행을 갔는데, 비가 왔어요!")
    private String contents;


}
