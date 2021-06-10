package com.example.wordbook.Repository.jparepository;

import com.example.wordbook.Domain.my.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJPARepository extends JpaRepository<Member,Long> {

}
