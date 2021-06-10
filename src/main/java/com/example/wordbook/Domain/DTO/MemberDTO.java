package com.example.wordbook.Domain.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Schema(description = "사용자")
@Getter
@Setter
@ToString
public class MemberDTO {


    @Schema(description = "구글에서 가져온 name 입니다.", defaultValue = "홍길동")
    @NotBlank(message = "name은 공백과 null 을 허용하지 않습니다.")
    private String name;

    @Schema(description = "구글에서 가져온 email 입니다.", defaultValue = "gildong@gamil.com")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Schema(description = "구글에서 가져온 사진의 URL 입니다.", defaultValue = "gildong@gmail.com/picture~~~")
    private String picture;

    /*@Enumerated(EnumType.STRING)
    private Role role;*/





}