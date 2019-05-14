package com.gobuk.springwebservice.service;

import com.gobuk.springwebservice.domain.posts.PostsRepository;
import com.gobuk.springwebservice.dto.posts.PostMainResponseDto;
import com.gobuk.springwebservice.dto.posts.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostsService {
    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<PostMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostMainResponseDto::new)
                .collect(Collectors.toList());
    }
}
