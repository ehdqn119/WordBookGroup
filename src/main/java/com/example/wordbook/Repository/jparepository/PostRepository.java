package com.example.wordbook.Repository.jparepository;


import com.example.wordbook.Domain.my.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    // 해당 이메일 -> 해당 여행 다이어리 내에 -> 포스트들을 반환합니다.
    // 여기서 묵시적 조인 발생하는 부분 지워줘야 합니다. ;


    @Query(value = "select p from Post p where p.journal.id = :id " +
            "and p.journal.member.email = :email")
    Page<Post> findPosts(@Param("email") String email, @Param("id") Long idx ,Pageable pageable);

    @Transactional(readOnly = false)
    void deleteAllByJournalMemberEmailAndJournalId(String email, Long id);

    Post findByJournalIdAndJournalMemberEmailAndId(Long id, String email, Long pIdx);

    @Transactional(readOnly = false)
    void deletePostByJournalIdAndJournalMemberEmailAndId(Long id, String email, Long pIdx);




}


