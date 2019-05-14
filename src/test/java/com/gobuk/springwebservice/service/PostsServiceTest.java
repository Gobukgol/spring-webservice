package com.gobuk.springwebservice.service;

import com.gobuk.springwebservice.domain.posts.Posts;
import com.gobuk.springwebservice.domain.posts.PostsRepository;
import com.gobuk.springwebservice.dto.posts.PostMainResponseDto;
import com.gobuk.springwebservice.dto.posts.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsServiceTest {

    @Autowired
    PostsService postsService;

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void Dto데이터가_posts테이블에_저장() {
        //given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .title("타이틀")
                .content("내용")
                .author("이메일")
                .build();
        //when
        postsService.save(dto);
        //then
        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
    }

    @Test
    public void 게시글_내림차순_불러오기() {
        //given
        postsRepository.save(Posts.builder()
                .title("first")
                .content("board")
                .author("example1@gmail.com")
                .build());

        postsRepository.save(Posts.builder()
                .title("second")
                .content("board")
                .author("example2@gmail.com")
                .build());
        //when
        List<PostMainResponseDto> postsList = postsService.findAllDesc();
        //then

        assertThat(postsList.get(0).getTitle()).isEqualTo("second");
        assertThat(postsList.get(1).getTitle()).isEqualTo("first");
    }
}