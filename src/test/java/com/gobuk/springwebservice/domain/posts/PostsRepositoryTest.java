package com.gobuk.springwebservice.domain.posts;

import com.gobuk.springwebservice.dto.posts.PostMainResponseDto;
import javafx.geometry.Pos;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        postsRepository.save(Posts.builder()
        .title("테스트 게시글")
        .content("테스트 내용")
        .author("이민형")
        .build());
        //when

        List<Posts> postsList = postsRepository.findAll();
        //then
        Posts findPost = postsList.get(2);
        assertThat(findPost.getTitle()).isEqualTo("테스트 게시글");
        assertThat(findPost.getContent()).isEqualTo("테스트 내용");
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
        .title("hi")
        .content("world")
        .author("exmaple@gmail.com")
        .build());
        //when
        List<Posts> postsList= postsRepository.findAll();
        //then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
    }
}