package com.example.wordbook.Repository;

import com.example.wordbook.Domain.Group;
import com.example.wordbook.Domain.GroupDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void getTest() {
        //given
        Group group = Group.builder()
                .id(1L)
                .build();
        //When
        Group groupTest = groupRepository.findById(group.getId()).get();

        //Then
        System.out.println(groupTest.toString());
        System.out.println(groupTest.getId()+"씌벌");

    }



}