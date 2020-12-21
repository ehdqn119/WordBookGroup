package com.example.wordbook.Controller;

import com.example.wordbook.Domain.Group;
import com.example.wordbook.Domain.GroupDto;
import com.example.wordbook.Service.GroupService;
import com.example.wordbook.util.DomainModel;
import com.example.wordbook.util.DomainRepresentationModelAssembler;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@Validated
@RequestMapping("/v1/api")
public class GroupController {

    @Autowired
    GroupService groupService;

    @Autowired
    DomainRepresentationModelAssembler assembler;

    // Collection (Ordering, filtering, Pageing) 기능 구현 할 것
    // 리턴 있음
    @GetMapping(value="/groups",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GroupDto>> getGroups() {
        List<GroupDto> AllGroup = groupService.getGroups();
        return new ResponseEntity<List<GroupDto>>(AllGroup,HttpStatus.OK);
    }

    //리턴 있음
    /*@GetMapping(value="/groups/{idx}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> getGroup(@PathVariable("idx") @Valid @Min(1) Long idx) {
        GroupDto groupDto = groupService.getGroup(idx);
        return new ResponseEntity<GroupDto>(groupDto,HttpStatus.OK);
    }*/

    @GetMapping(value="/groups/{idx}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DomainModel> getGroup(@PathVariable("idx") @Valid @Min(1) Long idx) {
        GroupDto groupDto = groupService.getGroup(idx);
        return ResponseEntity.ok().body(assembler.toModel(groupDto));
    }

    // 리턴 없음
    @PostMapping(value="/groups",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveGroup(@RequestBody @Valid GroupDto groupDto) {
        groupService.saveGroup(groupDto);
        return ResponseEntity.ok().body(assembler.toModel(groupDto));
    }

    // 리턴 없음 (애매해서 논의 필요)
    @PatchMapping(value="/groups/{idx}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity UpdateGroup(@PathVariable("idx") @Valid @Min(1) Long idx, @RequestBody @Valid GroupDto groupDto) {
        groupService.UpdateGroup(idx,groupDto);
        return ResponseEntity.ok().body(assembler.toModel(groupDto));
    }

    // 리턴 없음
    @DeleteMapping(value="/groups/{idx}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity DeleteGroup(@PathVariable("idx") @Valid @Min(1) Long idx) {
        GroupDto groupDto = groupService.DeleteGroup(idx);
        return ResponseEntity.ok().body(assembler.toModel(groupDto));
    }

}
