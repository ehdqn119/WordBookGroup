package com.example.wordbook.Controller;


import com.example.wordbook.Configuraiton.SecuredRestController;
import com.example.wordbook.Domain.my.Journal;
import com.example.wordbook.Domain.my.Post;
import com.example.wordbook.Repository.jparepository.PostRepository;
import com.example.wordbook.Repository.my.JournalRepository;
import com.example.wordbook.Service.PostService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1")
public class PostController implements SecuredRestController {

    private final JournalRepository journalRepository;
    private final PostRepository postRepository;
    private final PagedResourcesAssembler<Post> assembler;
    private final PostService postService;


    @Operation(summary = "다이어리 내에 해당하는 모든 포스트들을 검색합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "모든 포스트가 조회 되었습니다.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @GetMapping(value = "{email}/journals/{idx}/posts")
    public ResponseEntity<PagedModel<EntityModel<Post>>> getPosts(@PathVariable @Valid @Email String email,
                                                                  @PathVariable @Valid @Min(0) Long idx,
                                                                  Pageable pageable) {
        Page<Post> posts = postRepository.findPosts(email, idx, pageable);
        PagedModel<EntityModel<Post>> entityModels = assembler.toModel(posts);
        return ResponseEntity.ok(entityModels);
    }


    @Operation(summary = "다이어리에 포스트를 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "다이어리가 등록되었습니다.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @PostMapping(value = "{email}/journals/{idx}/posts")
    public Long savePost(@PathVariable @Valid @Email String email,
                         @PathVariable @Min(0) Long idx,
                         @RequestBody Post reqPost) {
        Long id =postService.savePost(email, idx, reqPost);
        return id;
    }


    @Operation(summary = "다이어리에 모든 포스트들을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "포스트가 삭제되었습니다.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @DeleteMapping(value = "{email}/journals/{idx}/posts/")
    public void deletePosts(@PathVariable @Valid @Email String email,
                            @PathVariable @Valid @Min(0) Long idx) {
        postRepository.deleteAllByJournalMemberEmailAndJournalId(email, idx);
    }



    @Operation(summary = "다이어리에 하나의 포스트를 검색합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "포스트가 검색되었습니다.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @GetMapping(value = "{email}/journals/{idx}/posts/{pIdx}")
    public Post getPost(@PathVariable @Valid @Email String email,
                        @PathVariable @Min(0) Long idx,
                        @PathVariable @Min(0) Long pIdx) {
        Post post = postRepository.findByJournalIdAndJournalMemberEmailAndId(idx, email, pIdx);
        return post;
    }


    @Operation(summary = "다이어리에 하나의 포스트를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "포스트가 수정되었습니다",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @PutMapping(value = "{email}/journals/{idx}/posts/{pIdx}")
    public Post updatePost(
            @PathVariable @Valid @Email String email,
            @PathVariable @Valid @Min(0) Long idx,
            @PathVariable @Valid @Min(0) Long pIdx,
            @RequestBody Post reqPost) {
        Post post = postRepository.findByJournalIdAndJournalMemberEmailAndId(idx, email, pIdx);
        post.setWeather(reqPost.getWeather());
        post.setTemperature(reqPost.getTemperature());
        post.setContents(reqPost.getContents());
        post.setTitle(reqPost.getTitle());
        post.setDay(reqPost.getDay());
        postRepository.save(post);
        return post;
    }

    @Operation(summary = "다이어리에 하나의 포스트를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "포스트가 삭제되었습니다.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @DeleteMapping(value = "{email}/journals/{idx}/posts/{pIdx}")
    public void deletePost(@PathVariable @Valid @Email String email,
                           @PathVariable @Min(0) Long idx,
                           @PathVariable @Min(0) Long pIdx) {
        postRepository.deletePostByJournalIdAndJournalMemberEmailAndId(idx, email, pIdx);

    }
}

