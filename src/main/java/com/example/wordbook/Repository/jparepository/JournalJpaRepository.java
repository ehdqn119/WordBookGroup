package com.example.wordbook.Repository.jparepository;

import com.example.wordbook.Domain.my.Journal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface JournalJpaRepository extends JpaRepository<Journal,Long> {

    Page<Journal> findBy(Pageable pageable);

    @Query(value = "select j from Journal j join j.member m where m.email = :email")
    Page<Journal> findEmail(@Param("email") String email,Pageable pageable);

    @Query(value = "select j from Journal j join j.member m where m.email = :email")
    List<Journal> findEmail(@Param("email") String email);

    @Query(value = "select j from Journal j join j.member m where m.email = :email " +
            "and j.id = :idx")
    Journal findByidMemberEmail(String email, Long idx);


    @Transactional(readOnly = false)
    void deleteAllByMemberEmail(String email);



}
