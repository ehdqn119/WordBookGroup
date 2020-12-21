package com.example.wordbook.Controller;

import com.example.wordbook.Domain.Group;
import com.example.wordbook.Domain.GroupDto;
import org.apache.catalina.User;
import org.apache.coyote.Response;
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

    // Collection (Ordering, filtering, Pageing) 기능 구현 할 것
    @GetMapping(value="groups",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GroupDto>> getGroups() {
        List<GroupDto> AllGroup = new ArrayList<>();
        return new ResponseEntity<List<GroupDto>>(AllGroup,HttpStatus.OK);
    }

    @GetMapping(value="groups/{idx}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> getGroup(@PathVariable("idx") @Valid @Min(1) Long idx) {
        Optional<GroupDto> group = Optional.ofNullable(new GroupDto());
        return new ResponseEntity<GroupDto>(group.get(),HttpStatus.OK);
    }

    @PostMapping(value="groups",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveGroup(@RequestBody @Valid GroupDto groupDto) {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PatchMapping(value="groups/{idx}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity UpdateGroup(@PathVariable("idx") @Valid @Min(1) Long idx) {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(value="groups/{idx}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity DeleteGroup(@PathVariable("idx") @Valid @Min(1) Long idx) {
        return new ResponseEntity(HttpStatus.OK);
    }

}
