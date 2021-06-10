package com.example.wordbook.Repository.my;

import com.example.wordbook.Domain.DTO.JournalDTO;
import com.example.wordbook.Domain.my.Journal;
import com.example.wordbook.Domain.my.JournalPost;
import com.example.wordbook.Domain.my.Member;
import com.example.wordbook.Exception.BusinessException.EntityNotFoundException;
import com.example.wordbook.Exception.BusinessException.JournalNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class JournalRepository {

    private final EntityManager em;
    private final MemberRepository memberRepository;


    // 해당 유저의 다이어리 저장
    @Transactional(readOnly = false)
    public void saveJournal(String email, JournalPost journalPost) {
        /*List<Member> memberList = memberRepository.findByEmail(email);
        if(memberList.isEmpty()) {
            throw new EntityNotFoundException("해당 다이어리는 존재하지 않습니다");
        } else {
            Journal journal = new Journal(member);
            journal.setDiaryTitle(journalPost.getDiaryTitle());
            journal.setPlaceName(journalPost.getPlaceName());
            journal.setStartDate(journalPost.getStartDate());
            journal.setEndDate(journalPost.getEndDate());
            journal.setTotalTemp(journalPost.getTotalTemp());
            journal.setThumbnailURL(journalPost.getThumbnailURL());
            em.persist(journal);
            return journal.getId();
        }*/
    }

    private void validateNotFoundUser(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 회원 email 입니다.");
        }
        if (findMembers == null) {
            return;
        }
    }

    // 다이어리 전체 수정은 없습니다.


    // 다이어리 전체 삭제
    @Transactional(readOnly = false)
    public void deleteJournals(String email) {
        List<Journal> journals = em.createQuery("select j from Journal j where j.member.email = :email",
                Journal.class)
                .setParameter("email", email)
                .getResultList();
        em.remove(journals);
    }




    // 해당 유저의 다이어리 중 하나를 검색합니다.
    public List<Journal> getJournal(String email, Long idx) {
        return em.createQuery("select j from Journal j where j.member.email = :email and j.id = :idx",
                Journal.class)
                .setParameter("email", email)
                .setParameter("idx", idx)
                .getResultList();
    }

    @Transactional(readOnly = false)
    public Journal updateJournal(String email, Long idx) {
        // 해당 유저가 가지고 있는 저널을 검색하고, 체이닝만 해주면 된다.
        List<Journal> journalList = getJournal(email, idx);
        if(journalList.isEmpty()) {
            throw new JournalNotFoundException("일치하는 다이어리가 없습니다.");
        } else {
            return journalList.get(0);
        }
    }


    // 해당 유저의 다이어리 중 하나를 삭제합니다.
    @Transactional(readOnly = false)
    public void deleteJournal(String email, Long idx) {
        Journal journal = em.createQuery("select j from Journal j where j.member.email = :email and j.id = :idx",
                Journal.class)
                .setParameter("email", email)
                .setParameter("idx", idx)
                .getSingleResult();
        em.remove(journal);
    }



}
