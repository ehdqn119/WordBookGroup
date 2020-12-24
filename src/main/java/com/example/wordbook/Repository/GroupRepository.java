package com.example.wordbook.Repository;

import com.example.wordbook.Domain.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {

    List<Group> findByIdGreaterThanOrderByIdDesc(Long id,Pageable pageable);

    List<Group> findByIdGreaterThan(Long id,Sort sort);

}
