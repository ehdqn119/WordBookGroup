package com.example.wordbook.Service;

import com.example.wordbook.Domain.GroupDto;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    public GroupDto getGroup(Long idx) {
        return new GroupDto();
    }


}
