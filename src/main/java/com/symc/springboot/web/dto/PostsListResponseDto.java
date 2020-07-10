package com.symc.springboot.web.dto;

import com.symc.springboot.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id ;
    private String title ;
    private String author ;
    private LocalDateTime modifiedDate ;

    public PostsListResponseDto(Posts entiry) {
        this.id = entiry.getId() ;
        this.title = entiry.getTitle();
        this.author = entiry.getAuthor();
        this.modifiedDate = entiry.getModifiedDate();
    }
}
