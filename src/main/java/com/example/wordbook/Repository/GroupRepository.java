package com.example.wordbook.Repository;

import com.example.wordbook.Domain.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GroupRepository extends JpaRepository<Group,Long>, JpaSpecificationExecutor<Group> {

    // 페이징 + 페이징 정렬
    List<Group> findByIdGreaterThanOrderByIdDesc(Long id,Pageable pageable);
    // 정렬만
    List<Group> findByIdGreaterThan(Long id,Sort sort);
    // 페이징 + 페이징 정렬
    Page<Group> findAll(Pageable pageable);


}
