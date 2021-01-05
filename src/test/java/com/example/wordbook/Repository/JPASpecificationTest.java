package com.example.wordbook.Repository;

import com.example.wordbook.Domain.Group;
import com.example.wordbook.util.GroupSpecification;
import com.example.wordbook.util.SearchCriteria;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application.properties")
public class JPASpecificationTest {

    @Autowired
    GroupRepository repository;

    private Group group1 = new Group();
    private Group group2 = new Group();

    @Before
    @DisplayName(value = "조건검색 테스트")
    public void init() {
        group1.setGroup_name("특수반");
        group1.setGroup_rule("특수권한");
        repository.save(group1);

        group2.setGroup_name("불편한반");
        group2.setGroup_rule("불편권한");
        repository.save(group2);
    }

    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        GroupSpecification spec =
                new GroupSpecification(new SearchCriteria("group_name", ":", "반"));
        List<Group> results = repository.findAll(spec);

        /*assertThat(Group1, isIn(results));
        assertThat(Group2, isIn(results));*/
    }

    @Test
    public void givenFirstAndLastName_whenGettingListOfUsers_thenCorrect() {
        GroupSpecification spec1 =
                new GroupSpecification(new SearchCriteria("firstName", ":", "john"));
        GroupSpecification spec2 =
                new GroupSpecification(new SearchCriteria("lastName", ":", "doe"));

        List<Group> results = repository.findAll(Specification.where(spec1).and(spec2));

        /*assertThat(group1, isIn(results));
        assertThat(group2, not(isIn(results)));*/
    }

    @Test
    public void givenLastAndAge_whenGettingListOfUsers_thenCorrect() {
        GroupSpecification spec1 =
                new GroupSpecification(new SearchCriteria("age", ">", "25"));
        GroupSpecification spec2 =
                new GroupSpecification(new SearchCriteria("lastName", ":", "doe"));

        List<Group> results =
                repository.findAll(Specification.where(spec1).and(spec2));

        /*assertThat(userTom, isIn(results));
        assertThat(userJohn, not(isIn(results)));*/
    }

    @Test
    public void givenWrongFirstAndLast_whenGettingListOfUsers_thenCorrect() {
        GroupSpecification spec1 =
                new GroupSpecification(new SearchCriteria("firstName", ":", "Adam"));
        GroupSpecification spec2 =
                new GroupSpecification(new SearchCriteria("lastName", ":", "Fox"));

        List<Group> results =
                repository.findAll(Specification.where(spec1).and(spec2));

        /*assertThat(userJohn, not(isIn(results)));
        assertThat(userTom, not(isIn(results)));*/
    }

    @Test
    public void givenPartialFirst_whenGettingListOfUsers_thenCorrect() {
        GroupSpecification spec =
                new GroupSpecification(new SearchCriteria("firstName", ":", "jo"));

        List<Group> results = repository.findAll(spec);

        /*assertThat(userJohn, isIn(results));
        assertThat(userTom, not(isIn(results)));*/
    }

}
