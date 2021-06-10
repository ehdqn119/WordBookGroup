package com.example.wordbook.Service;


import com.example.wordbook.Domain.DTO.JournalDTO;
import com.example.wordbook.Domain.my.Journal;
import com.example.wordbook.Domain.my.Member;
import com.example.wordbook.Domain.my.Post;
import com.example.wordbook.Exception.BusinessException.JournalNotFoundException;
import com.example.wordbook.Exception.BusinessException.UserNotFoundException;
import com.example.wordbook.Repository.jparepository.JournalJpaRepository;
import com.example.wordbook.Repository.jparepository.PostRepository;
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
public class PostService {

    private final MemberRepository memberRepository;
    private final JournalRepository journalRepository;
    private final JournalJpaRepository journalJpaRepository;
    private final PostRepository postRepository;


    public Long savePost(String email, Long idx, Post reqPost) {
        List<Journal> journalList = journalRepository.getJournal(email, idx);
        if(journalList.isEmpty()) {
            throw new JournalNotFoundException("찾는 다이어리가 없습니다!");
        }
        Journal journal = journalList.get(0);
        Post post = new Post();
        post.setTitle(reqPost.getTitle());
        post.setTemperature(reqPost.getTemperature());
        post.setContents(reqPost.getContents());
        post.setWeather(reqPost.getWeather());
        post.setDay(reqPost.getDay());
        post.setJournal(journal);
        postRepository.save(post);
        return post.getId();
    }
}
