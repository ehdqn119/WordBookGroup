package com.example.wordbook.Repository.my;

import com.example.wordbook.Domain.my.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }


    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name",
                Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Member> findByEmail(String email) {
        return em.createQuery("select m from Member m where m.email = :email",
                Member.class)
                .setParameter("email", email)
                .getResultList();
    }


    @Transactional(readOnly = false)
    public Member updateMember(Member member) {
        Member changeMember = findOne(member.getId());
        changeMember.setEmail(member.getEmail());
        changeMember.setName(member.getName());
        changeMember.setPicture(member.getPicture());
        em.merge(changeMember);
        em.flush();
        return changeMember;
    }

    @Transactional(readOnly = false)
    public Long delete(Long id) {
        Member member = findOne(id);
        em.remove(member);
        return member.getId();
    }

}