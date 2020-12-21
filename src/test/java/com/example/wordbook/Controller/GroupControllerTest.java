package com.example.wordbook.Controller;

import com.example.wordbook.Domain.GroupDto;
import com.example.wordbook.Service.GroupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(GroupControllerTest.class)
public class GroupControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    GroupService groupService;

    @Test
    public void getGroupTest() throws Exception {

        //given
        GroupDto groupDto = GroupDto.builder()
            .name("중앙로책방스터디")
            .build();

        //given
        given(groupService.getGroup(-1L)).willReturn(groupDto);

        //when
        ResultActions rs = mockMvc.perform(get("/v1/api/groups/0")
                .content(objectMapper.writeValueAsString(groupDto)));

        //then
        rs.andDo(print());


    }

}