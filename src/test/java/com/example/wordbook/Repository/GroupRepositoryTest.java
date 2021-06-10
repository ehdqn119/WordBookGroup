/*
package com.example.wordbook.Repository;

import com.example.wordbook.Domain.Group;
import com.example.wordbook.Domain.GroupDto;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application.properties")
public class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    @DisplayName("전체조회")
    public void getAllGroupsTest() {
        //given
        List<Group> groupTest = groupRepository.findAll();
        //When
        //Then
        System.out.println(groupTest.toString());
    }

    @Test
    @DisplayName("전체 페이징 조회")
    public void getAllGroupsPageingTest() {
        //given
        Pageable pageable = PageRequest.of(0,10,Sort.by("id").descending());
        List<Group> groupList = groupRepository.findByIdGreaterThanOrderByIdDesc(0L,pageable);
        System.out.println(groupList.toString());
    }

    @Test
    @DisplayName("전체 정렬 조회")
    public void getAllGroupsSortingTest() {
        //given
        List<Group> groupDescList = groupRepository.findByIdGreaterThan(0L,Sort.by("id").ascending());
        System.out.println(groupDescList.toString());
    }



    @Test
    @DisplayName("한명조회")
    public void getGroupsTest() {
        //given
        Group group = Group.builder()
                .id(1L)
                .build();
        //When
        Group groupTest = groupRepository.findById(group.getId()).get();

        //Then
        System.out.println(groupTest.toString());
    }

    @Test
    @DisplayName("등록")
    public void postGroupsTest() {
        //given
        Group group = Group.builder()
                .group_name("컴퓨터공학용어")
                .group_rule("모든권한")
                .build();
        //When
        Group groupTest = groupRepository.save(group);

        //Then
        System.out.println(groupTest.toString());
    }

    @Test
    @DisplayName("업데이트")
    public void updateGroupsTest() {
        //given
        Group group = Group.builder()
                .group_name("계명대학교컴퓨터공학용어집")
                .group_rule("읽기/쓰기권한")
                .build();
        Group groupTest = groupRepository.findById(1L).get();
        System.out.println(groupTest.toString());
        //when
        groupTest.setGroup_name(group.getGroup_name());
        groupTest.setGroup_rule(group.getGroup_rule());
        Group returnGroup = groupRepository.save(groupTest);
        //then
        System.out.println(returnGroup.toString());
    }

    @Test
    @DisplayName("삭제")
    public void deleteGroupsTest() {
        //given
        Long deleteId = 1L;
        //when
        groupRepository.deleteById(deleteId);
        //Then

    }




}*/
