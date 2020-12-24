package com.example.wordbook.Service;

import com.example.wordbook.Domain.Group;
import com.example.wordbook.Domain.GroupDto;
import com.example.wordbook.Repository.GroupRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ModelMapper modelMapper;

    // 확정 : 있어야 한다. 모델 메퍼 관련 사항 : https://blog.woniper.net/319
    public Page<GroupDto> getGroups(Pageable pageable) {
        /*List<Group> groups = new ArrayList<>();*/
        pageable = PageRequest.of(0,10, Sort.by("id").descending());
        Page<Group> groups = groupRepository.findAll(pageable);
        Page<GroupDto> groupDtos = modelMapper.map(groups, new TypeToken<Page<GroupDto>>(){}.getType());
        return groupDtos;
    }

    public Page<Group> testHateOas(Pageable pageable) {
        Page<Group> test = groupRepository.findAll(pageable);
        return test;
    }

    // 확정 : 리턴 값 있어야 한다.
    public GroupDto getGroup(Long idx) {
        Group group = groupRepository.findById(idx).get();
        GroupDto groupDto = modelMapper.map(group,GroupDto.class);
        return groupDto;
    }

    // 확정 : 리턴 값 없어도 된다. (HATEOAS로 Content-Location 구현)
    public void saveGroup(GroupDto groupDto) {
        Group group = modelMapper.map(groupDto,Group.class);
        groupRepository.save(group);
    }

    // 애는 모르겠다 (https://tools.ietf.org/html/rfc5789#section-3.2) 확인
    public void UpdateGroup(Long idx, GroupDto groupDto) {
        Group group = groupRepository.findById(idx).get();
        group.setGroup_name(groupDto.getGroup_name());
        group.setGroup_rule(groupDto.getGroup_rule());
        groupRepository.save(group);
    }

    // 확정 : 리턴 값 없어도 된다.
    public GroupDto DeleteGroup(Long idx) {
        Group group = groupRepository.findById(idx).get();
        groupRepository.deleteById(idx);
        GroupDto groupDto = modelMapper.map(group,GroupDto.class);
        return groupDto;
    }




}
