package com.example.wordbook.Service;


import com.example.wordbook.Domain.DTO.JournalDTO;
import com.example.wordbook.Domain.my.Journal;
import com.example.wordbook.Domain.my.Member;
import com.example.wordbook.Exception.BusinessException.JournalNotFoundException;
import com.example.wordbook.Exception.BusinessException.UserNotFoundException;
import com.example.wordbook.Repository.jparepository.JournalJpaRepository;
import com.example.wordbook.Repository.my.JournalRepository;
import com.example.wordbook.Repository.my.MemberRepository;
import com.example.wordbook.util.mapper.JournalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class JournalService {

    private final MemberRepository memberRepository;
    private final JournalRepository journalRepository;
    private final JournalJpaRepository journalJpaRepository;

    // 유저 벨리데이션
    public void userValidation(List<Member> list) {
        if(list.isEmpty()) {
            throw new UserNotFoundException("존재하지 않는 유저 입니다.");
        }
    }


    // 저널 벨리데이션
    public void journalValidation(List<Journal> list) {
        if(list.isEmpty()) {
            throw new JournalNotFoundException("존재하지 않는 다이어리 입니다.");
        }
    }


    // 전체 조회
    // 해당 전체가 있을 경우 : 검색완료 처리
    // 해당 전체가 없을 경우 : Exception 처리
    public Page<Journal> getJournals(String email, Pageable pageable) {
        Page<Journal> journalList = journalJpaRepository.findEmail(email, pageable);
        return journalList;
    }


    // 단건 등록
    // 있을 경우 : 등록 완료 처리
    // 없을 경우 : 에러처리가 필요하지 않음
    // 있지만, 파라미터 값이 잘못된 경우 : Exception 처리
    public Long saveJournal(String email, JournalDTO journalDTO) {
        List<Member> memberList = memberRepository.findByEmail(email);
        userValidation(memberList);
        Journal journal = JournalMapper.INSTANCE.journalDTOtoEntity(journalDTO);
        journal.setMember(memberList.get(0));
        // 이 부분 다시 리팩토링 해야 합니다. //
        Journal journal1 = journalJpaRepository.save(journal);
        return journal1.getId();
    }

    // 전체 삭제
    // 있을 경우 : 삭제 완료 처리
    // 없을 경우 : Exception 처리
    public void deleteJournals(String email) {
        // 삭제할 저널이 있는지 확인해준다.
        List<Journal> journalList = journalJpaRepository.findEmail(email);
        journalValidation(journalList);
        // 삭제할 저널이 있다면 삭제한다.
        journalJpaRepository.deleteAllByMemberEmail(email);
    }





    // 단건조회
    // 해당 다이어리가 있을 경우 : 검색완료 처리
    // 해당 다이어리가 없을 경우 : Exception 처리
    public JournalDTO getJournal(String email, Long idx) {
        List<Journal> journalList = journalRepository.getJournal(email, idx);
        journalValidation(journalList);
        JournalDTO journalDTO = JournalMapper.INSTANCE.journaltoJournalDTO(journalList.get(0));
        return journalDTO;
    }


    // 단건 수정
    // 해당 다이어리가 있을 경우 : 수정 완료 처리
    // 해당 다이어리가 없을 경우 : Exception 처리
    // 해당 다이어리에 매핑 된 값이 맞지 않은 형식일 경우 : Exception 처리
    public JournalDTO updateJournal(String email, Long idx, JournalDTO journalDTO) {
        Journal journal = journalRepository.updateJournal(email, idx);
        Journal newJournal = JournalMapper.INSTANCE.journalDTOtoEntity(journalDTO);
        System.out.println(newJournal.toString());
        journal.setTotalTemp(newJournal.getTotalTemp());
        journal.setThumbnailURL(newJournal.getThumbnailURL());
        journal.setDiaryTitle(newJournal.getDiaryTitle());
        journal.setStartDate(newJournal.getStartDate());
        journal.setEndDate(newJournal.getEndDate());
        journal.setPlaceName(newJournal.getPlaceName());
        journalJpaRepository.save(journal);
        return JournalMapper.INSTANCE.journaltoJournalDTO(journal);
    }


    // 단건 삭제
    // 해당 다이어리가 있을 경우 : 삭제 완료 처리
    // 해당 다이어리가 없을 경우 : Exception 처리
    public void deleteJournal(String email, Long idx) {
        List<Member> memberList = memberRepository.findByEmail(email);
        userValidation(memberList);
        List<Journal> journalList = journalRepository.getJournal(email,idx);
        journalValidation(journalList);
        journalRepository.deleteJournal(email, idx);
    }
}
