package com.example.wordbook.Service;

import com.example.wordbook.Domain.my.Member;
import com.example.wordbook.Repository.jparepository.MemberJPARepository;
import com.example.wordbook.Repository.my.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final MemberRepository memberRepository;
    private final MemberJPARepository memberJPARepository;
    /**
     * 회원가입
     */

    @Transactional //변경
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberJPARepository.save(member);
        return member.getId();
    }


    private void validateDuplicateMember(Member member) {
        List<Member> findMembers =
                memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }






        /**
         * 전체 회원 조회
         */
    public List<Member> findUsers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long userId) {
        return memberRepository.findOne(userId);
    }


    /**
     * 멤버를 수정합니다.
     * @param member
     * @return member
     */
    @Transactional(readOnly = false)
    public Member updateMember(Member member) {
        notFoundMember(member.getId());
        memberRepository.updateMember(member);
        return member;
    }

    private void notFoundMember(Long id) {
        Member findMembers = memberRepository.findOne(id);
        System.out.println(findMembers);
        if (findMembers == null) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
    }
}
