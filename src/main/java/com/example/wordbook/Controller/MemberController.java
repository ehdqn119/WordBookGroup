package com.example.wordbook.Controller;


import com.example.wordbook.Domain.my.Member;
import com.example.wordbook.Repository.my.MemberRepository;
import com.example.wordbook.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Validated
public class MemberController {


    private final UserService userService;
    private final MemberRepository memberRepository;


    @Operation(summary = "모든 유저의 정보를 반환합니다." , security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공 시 모든 유저 정보와 200 을 리턴합니다.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Member.class)) }),
            @ApiResponse(responseCode = "404", description = "모든 유저의 정보를 찾을 수 없습니다.",
                    content = @Content) })
    @GetMapping(value="/users")
    public List<Member> getUsers() {
        List<Member> members = userService.findUsers();

        return members;
    }



    @Operation(summary = "유저의 정보를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "해당 유저의 정보가 리턴됩니다.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Member.class)) }),
            @ApiResponse(responseCode = "400", description = "해당 유저가 존재 하지 않습니다.",
                    content = @Content)})
    @GetMapping(value="/users/{idx}")
    public Member getUser(@PathVariable("idx") @Valid @Min(0) Long idx) {
        Member user = userService.findOne(idx);
        List<Member> members = memberRepository.findByName("ddasd");
        members.isEmpty();
        return user;
    }


    @Operation(summary = "회원 가입을 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원가입이 완료되었습니다.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Member.class)) }),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @PostMapping(value="/users")
    public ResponseEntity saveUser(@RequestBody Member member) {
        Long id = userService.join(member);
        return ResponseEntity.noContent().header("Location", "users/"+id).build();
    }


    @Operation(summary = "유저의 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정한 유저의 id를 리턴합니다.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Member.class)) }),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터 값이 들어왔습니다.",
                    content = @Content)})
    @PutMapping(value="/users/{idx}")
    public ResponseEntity<Member> updateUser(@RequestBody @Valid Member member) {
        Member member1 = userService.updateMember(member);
        return ResponseEntity.created(URI.create("/users"+member1.getId())).body(member1);
    }


    @Operation(summary = "유저의 정보를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "삭제 된 경우 204만 리턴합니다.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Member.class)) }),
            @ApiResponse(responseCode = "400", description = "해당 유저를 찾을 수 없습니다.",
                    content = @Content)})
    @DeleteMapping(value="/users/{idx}")
    public ResponseEntity DeleteGroup(@PathVariable("idx") @Valid @Min(1) Long idx) {
        Long id = memberRepository.delete(idx);
        return ResponseEntity.noContent().header("Location ","users/"+id).build();
    }






}
