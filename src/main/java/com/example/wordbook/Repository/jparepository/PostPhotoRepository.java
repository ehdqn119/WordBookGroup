package com.example.wordbook.Repository.jparepository;

import com.example.wordbook.Domain.my.PostPhoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PostPhotoRepository extends JpaRepository<PostPhoto,Long> {

    Page<PostPhoto> findAllByPostIdAndPostJournalIdAndPostJournalMemberEmail(
            Long id,
            Long jId,
            String email,
            Pageable pageable
    );

    @Transactional(readOnly = false)
    void deleteAllByPostIdAndPostJournalIdAndPostJournalMemberEmail(
            Long pId,
            Long jId,
            String email
    );
    
    // 단건 조회
    PostPhoto findByPostJournalIdAndPostIdAndIdAndPostJournalMemberEmail(
            Long id,
            Long jid,
            Long pid,
            String email
    );
    
    // 단건 삭제
    @Transactional(readOnly = false)
    void deleteByPostJournalIdAndPostIdAndIdAndPostJournalMemberEmail (
            Long id,
            Long jid,
            Long pid,
            String email
    );



}
