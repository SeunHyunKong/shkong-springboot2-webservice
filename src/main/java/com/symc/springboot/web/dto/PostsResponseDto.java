package com.symc.springboot.web.dto;

import com.symc.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id ;
    private String title ;
    private String contents ;
    private String author ;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId() ;
        this.title = entity.getTitle();
        this.contents = entity.getContents() ;
        this.author = entity.getAuthor();
    }
}
