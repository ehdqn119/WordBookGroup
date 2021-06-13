package com.example.wordbook.Controller;

import com.example.wordbook.Domain.DTO.JournalDTO;
import com.example.wordbook.Domain.my.Journal;
import com.example.wordbook.Service.JournalService;
import com.example.wordbook.util.hateoas.JournalAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api/v1")
public class JournalController {

    private final PagedResourcesAssembler<Journal> assembler;
    private final JournalAssembler journalAssembler;
    private final JournalService journalService;



    @Operation(summary = "해당 유저의 모든 다이어리를 검색합니다.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "다이어리 검색이 완료되었습니다.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JournalDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @GetMapping(value="/{email}/journals")
    public ResponseEntity<PagedModel<EntityModel<Journal>>> getJournals(@PathVariable @Valid @Email String email,
                                                                        Pageable pageable) {
        Page<Journal> journals = journalService.getJournals(email, pageable);
        PagedModel<EntityModel<Journal>> entityModels = assembler.toModel(journals);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal.toString());

        return ResponseEntity.ok(entityModels);
    }



    @Operation(summary = "다이어리를 등록 합니다." )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "다이어리가 등록되었습니다.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JournalDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @PostMapping(value="{email}/journals")
    public ResponseEntity<Long> saveJournal(@PathVariable @Valid @Email String email,
                            @RequestBody JournalDTO journalDTO) {
       Long id = journalService.saveJournal(email,journalDTO);
       return ResponseEntity.ok(id);

    }



    @Operation(summary = "해당 유저의 모든 다이어리를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "전체 다이어리가 삭제 되었습니다.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JournalDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @DeleteMapping(value="{email}/journals")
    public void deleteJournals(@PathVariable @Valid @Email String email) {
        journalService.deleteJournals(email);
    }


    @Operation(summary = "해당 유저의 다이어리 중 하나를 검색합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "다이어리 검색이 완료되었습니다.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JournalDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @GetMapping(value="/{email}/journals/{idx}")
    public ResponseEntity getJournal(@PathVariable @Valid @Email String email,
                                              @PathVariable @Valid @Min(0) Long idx) {
        JournalDTO journalDTO = journalService.getJournal(email,idx);
        return ResponseEntity.ok().body(journalAssembler.toModel(journalDTO,email,idx));
    }


    @Operation(summary = "해당 유저의 다이어리 중 하나를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "다이어리 검색이 완료되었습니다.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JournalDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @PutMapping(value="/{email}/journals/{idx}")
    public ResponseEntity updateJournal(@PathVariable @Valid @Email String email,
                                                 @PathVariable @Valid @Min(0) Long idx,
                                                 @RequestBody JournalDTO journalDTO) {
        JournalDTO newjournalDTO = journalService.updateJournal(email,idx,journalDTO);
        return ResponseEntity.ok().body(journalAssembler.toModel(newjournalDTO));
    }


    @Operation(summary = "해당 유저의 다이어리 중 하나를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "해당 다이어리가 조회되었습니다.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JournalDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터가 들어왔습니다.",
                    content = @Content)})
    @DeleteMapping(value="{email}/journals/{idx}")
    public ResponseEntity deleteJournal(@PathVariable @Valid @Email String email,
                              @PathVariable @Valid @Min(0) Long idx) {
        journalService.deleteJournal(email,idx);
        return ResponseEntity.noContent().header("Location","localhost:8928/v1/api/groups").build();
    }




}
