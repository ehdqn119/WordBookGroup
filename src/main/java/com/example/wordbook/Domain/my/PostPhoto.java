package com.example.wordbook.Domain.my;


import com.example.wordbook.entity.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter @Setter
public class PostPhoto extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_photo_id")
    private Long id;

    @Schema(description = "사진의 URL 입니다.", defaultValue = "url('//d1.awsstatic.com/Digital Marketing/acquisition/2018/Free Tier/Heroes/WEB_FreeTier_Yellow_Hero_1400x500.eff27f1d5e226d558fc858f0b9b03f9417dfc826.jpg')")
    private String photoURL;

    @Schema(description = "썸네일 제목입니다.", defaultValue = "다보탑")
    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

}
