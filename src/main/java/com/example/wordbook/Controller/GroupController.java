package com.example.wordbook.Controller;

import com.example.wordbook.Domain.Group;
import com.example.wordbook.Domain.GroupDto;
import com.example.wordbook.Service.GroupService;
import com.example.wordbook.util.DomainModel;
import com.example.wordbook.util.DomainRepresentationModelAssembler;
import com.example.wordbook.util.GroupSpecificaionBuilder;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@Validated
@RequestMapping("/v1/api")
public class GroupController {

    @Autowired
    GroupService groupService;

    @Autowired
    DomainRepresentationModelAssembler assembler;


    @RequestMapping(method = RequestMethod.GET, value = "/users")
    @ResponseBody
    public List<User> search(@RequestParam(value = "search") String search) {
        GroupSpecificaionBuilder builder = new GroupSpecificaionBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Group> spec = builder.build();
        return groupService.findAll(spec);
    }

    // Collection (Ordering, filtering, Pageing) 기능 구현 할 것
    // 리턴 있음
    @GetMapping(value="/groups",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedModel<GroupDto>> getGroups(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<GroupDto> AllGroup = groupService.getGroups(pageable);
        return new ResponseEntity<PagedModel<GroupDto>>(assembler.toModel(AllGroup),HttpStatus.OK);
    }

    //리턴 있음
    /*@GetMapping(value="/groups/{idx}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> getGroup(@PathVariable("idx") @Valid @Min(1) Long idx) {
        GroupDto groupDto = groupService.getGroup(idx);
        return new ResponseEntity<GroupDto>(groupDto,HttpStatus.OK);
    }*/

    // 리턴 있음
    @GetMapping(value="/groups/{idx}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DomainModel> getGroup(@PathVariable("idx") @Valid @Min(1) Long idx) {
        GroupDto groupDto = groupService.getGroup(idx);
        return ResponseEntity.ok().body(assembler.toModel(groupDto));
    }

    // 리턴 없음, 헤이토스로 관리하면 비 효율적
    @PostMapping(value="/groups",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DomainModel> saveGroup(@RequestBody @Valid GroupDto groupDto) {
        groupService.saveGroup(groupDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 리턴 없음 (애매해서 논의 필요)
    @PatchMapping(value="/groups/{idx}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity UpdateGroup(@PathVariable("idx") @Valid @Min(1) Long idx, @RequestBody @Valid GroupDto groupDto) {
        GroupDto groupDto1 = groupService.UpdateGroup(idx,groupDto);
        return ResponseEntity.ok().body(assembler.toModel(groupDto1));
    }

    // 리턴 없음, 헤이토스로 관리하면 비 효율적.
    @DeleteMapping(value="/groups/{idx}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity DeleteGroup(@PathVariable("idx") @Valid @Min(1) Long idx) {
        GroupDto groupDto = groupService.DeleteGroup(idx);
        //Content-Location 으로 처음 시작 지점 URI 보내줘야 겠다. //
        return ResponseEntity.noContent().header("Location ","localhost:9292/v1/api/groups").build();
    }

}
