package com.example.wordbook.Controller;


import com.example.wordbook.Domain.my.Post;
import com.example.wordbook.Domain.my.PostPhoto;
import com.example.wordbook.Repository.jparepository.PostPhotoRepository;
import com.example.wordbook.Repository.jparepository.PostRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class PostPhotoController {

    private final PagedResourcesAssembler<PostPhoto> assembler;
    private final PostRepository postRepository;
    private final PostPhotoRepository postPhotoRepository;

    // 모든 이미지 조회
    @Operation(summary = "포스트에 해당하는 이미지의 정보를 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "모든 포스트가 조회 되었습니다.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostPhoto.class))}),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @GetMapping(value = "{email}/journals/{jid}/posts/{pid}/photos")
    public ResponseEntity<PagedModel<EntityModel<PostPhoto>>> getPosts(
            @PathVariable @Valid @Email String email,
            @PathVariable @Valid @Min(0) Long jid,
            @PathVariable @Valid @Min(0) Long pid,
            Pageable pageable) {
        Page<PostPhoto> postPhotos =
                postPhotoRepository.findAllByPostIdAndPostJournalIdAndPostJournalMemberEmail(pid,jid,email,pageable);
        PagedModel<EntityModel<PostPhoto>> entityModels = assembler.toModel(postPhotos);
        return ResponseEntity.ok(entityModels);
    }

    // 이미지등록
    @Operation(summary = "포스트에 해당하는 이미지의 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "이미지가 등록되었습니다.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostPhoto.class))}),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @PostMapping(value = "{email}/journals/{jid}/posts/{pid}/photos")
    public void savePostPhoto(@PathVariable @Valid @Email String email,
                              @PathVariable @Valid @Min(0) Long jid,
                              @PathVariable @Valid @Min(0) Long pid,
                              @RequestBody PostPhoto reqPostPhoto) {
        Post post = postRepository.findByJournalIdAndJournalMemberEmailAndId(jid,email,pid);
        PostPhoto postPhoto = new PostPhoto();
        postPhoto.setPhotoURL(reqPostPhoto.getPhotoURL());
        postPhoto.setThumbnail(reqPostPhoto.getThumbnail());
        postPhoto.setPost(post);
        postPhotoRepository.save(postPhoto);
    }


    // 모든 이미지 삭제
    @Operation(summary = "해당 포스트의 모든 이미지를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "포스트가 삭제되었습니다.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostPhoto.class))}),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @DeleteMapping(value = "{email}/journals/{jid}/posts/{pid}/photos")
    public void deletePosts(
            @PathVariable @Valid @Email String email,
            @PathVariable @Valid @Min(0) Long jid,
            @PathVariable @Valid @Min(0) Long pid
            ) {
        postPhotoRepository.deleteAllByPostIdAndPostJournalIdAndPostJournalMemberEmail(pid, jid, email);
    }


    // Document Line //


    // 하나의 포스트 반환
    @Operation(summary = "해당 포스트에 해당하는 하나의 이미지를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "포스트가 검색되었습니다.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostPhoto.class))}),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @GetMapping(value = "{email}/journals/{jid}/posts/{pid}/photos/{tid}")
    public ResponseEntity getPostPhoto(
            @PathVariable @Valid @Email String email,
            @PathVariable @Valid @Min(0) Long jid,
            @PathVariable @Valid @Min(0) Long pid,
            @PathVariable @Valid @Min(0) Long tid) {

       PostPhoto postPhoto = postPhotoRepository.findByPostJournalIdAndPostIdAndIdAndPostJournalMemberEmail(jid,pid,tid,email);
       return ResponseEntity.ok(postPhoto);
    }


    // 하나의 포스트 수정
    @Operation(summary = "다이어리에 하나의 이미지를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "포스트가 검색되었습니다.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostPhoto.class))}),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @PutMapping(value = "{email}/journals/{jid}/posts/{pid}/photos/{tid}")
    public ResponseEntity<PostPhoto> updatePostPhoto(
            @PathVariable @Valid @Email String email,
            @PathVariable @Valid @Min(0) Long jid,
            @PathVariable @Valid @Min(0) Long pid,
            @PathVariable @Valid @Min(0) Long tid,
            @RequestBody PostPhoto postPhoto) {
        PostPhoto updatePostPhoto = postPhotoRepository.findByPostJournalIdAndPostIdAndIdAndPostJournalMemberEmail(jid,pid,tid,email);
        updatePostPhoto.setPhotoURL(postPhoto.getPhotoURL());
        updatePostPhoto.setThumbnail(postPhoto.getThumbnail());
        postPhotoRepository.save(updatePostPhoto);
        return ResponseEntity.ok(updatePostPhoto);
    }



    // 하나의 포스트를 삭제합니다.
    @Operation(summary = "포스트의 사진 중 하나의 사진을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "이미지가 삭제되었습니다.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostPhoto.class))}),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @DeleteMapping(value = "{email}/journals/{jid}/posts/{pid}/posts/{tid}")
    public void deletePostPhoto(
            @PathVariable @Valid @Email String email,
            @PathVariable @Valid @Min(0) Long jid,
            @PathVariable @Valid @Min(0) Long pid,
            @PathVariable @Valid @Min(0) Long tid) {
        postPhotoRepository.deleteByPostJournalIdAndPostIdAndIdAndPostJournalMemberEmail(jid, pid, tid, email);

    }


}
