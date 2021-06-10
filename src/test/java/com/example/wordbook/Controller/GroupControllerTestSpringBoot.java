/*
package com.example.wordbook.Controller;

import com.example.wordbook.Domain.GroupDto;
import com.example.wordbook.Service.GroupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class GroupControllerTestSpringBoot {

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    GroupService groupService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void ConstraintViolationExceptionGroupTest() throws Exception {

        //given

        //When
        mockMvc.perform(get("/v1/api/groups/0"))
                .andExpect(status().isBadRequest())
                .andDo(print());
        //Then
    }

    @Test
    public void MethodArgumentNotValidExceptionGroupTest() throws Exception {

        //given
        GroupDto groupDto = GroupDto.builder()
                .group_name("의학스터디")
                .build();
        String grouptdtoString = objectMapper.writeValueAsString(groupDto);

        //When
        mockMvc.perform(post("/v1/api/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(grouptdtoString))
                .andExpect(status().isBadRequest())
                .andDo(print());
        //Then
    }




}*/
