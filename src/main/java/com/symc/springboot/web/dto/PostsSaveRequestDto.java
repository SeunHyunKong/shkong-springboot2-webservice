package com.symc.springboot.web.dto;

import com.symc.springboot.domain.BaseTimeEntity;
import com.symc.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title ;
    private String contents ;
    private String author ;

    @Builder
    public PostsSaveRequestDto(String title, String contents, String author) {
        this.title = title ;
        this.contents = contents ;
        this.author = author ;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .contents(contents)
                .author(author)
                .build();
    }
}
