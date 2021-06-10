package com.example.wordbook.Domain.my;


import com.example.wordbook.entity.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Journal extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journal_id")
    private Long id;

    @Schema(description = "다이어리의 제목입니다.", defaultValue = "여행을 가다.")
    @NotBlank(message = "다이어리의 제목은 공백과 null 을 허용하지 않습니다.")
    private String diaryTitle;

    @Schema(description = "여행한 여행지 입니다.", defaultValue = "대구")
    @NotBlank(message = "여행지는 공백과 null 을 허용하지 않습니다.")
    private String placeName;

    @Schema(description = "여행을 시작한 날짜 입니다.")
    private LocalDateTime startDate;

    @Schema(description = "여행을 마친 마지막 날짜 입니다.")
    private LocalDateTime endDate;

    @Schema(description = "해당 여행의 온도 입니다.", defaultValue = "36.5")
    private Long totalTemp;

    @Schema(description = "여행을 다녀온 이미지 입니다.", defaultValue = "https:localhost:8928//asd.com")
    @NotBlank(message = "이미지는 공백과 null 을 허용하지 않습니다.")
    private String thumbnailURL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    @OneToMany(mappedBy = "journal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Post> postList = new ArrayList<>();


    // 연관관계 메소드 //
    public Journal(Member member) {
        this.member = member;
    }
}
