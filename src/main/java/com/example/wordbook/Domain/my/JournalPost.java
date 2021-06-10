package com.example.wordbook.Domain.my;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class JournalPost {

    @Schema(description = "다이어리의 제목입니다.", defaultValue = "여행을 가다.")
    @NotBlank(message = "다이어리의 제목은 공백과 null 을 허용하지 않습니다.")
    private String diaryTitle;

    @Schema(description = "여행한 여행지 입니다.", defaultValue = "대구")
    @NotBlank(message = "여행지는 공백과 null 을 허용하지 않습니다.")
    private String placeName;

    @Schema(description = "여행을 시작한 날짜 입니다.")
    @NotBlank(message = "여행을 시작한 날짜는 공백과 null 을 허용하지 않습니다.")
    private LocalDateTime startDate;

    @Schema(description = "여행을 마친 마지막 날짜 입니다.")
    @NotBlank(message = "여행을 마친 날짜는 공백과 null을 허용하지 않습니다.")
    private LocalDateTime endDate;

    @Schema(description = "해당 여행의 온도 입니다.", defaultValue = "36.5")
    private Long totalTemp;

    @Schema(description = "여행을 다녀온 이미지 입니다.", defaultValue = "https:localhost:8928//asd.com")
    private String thumbnailURL;
}
