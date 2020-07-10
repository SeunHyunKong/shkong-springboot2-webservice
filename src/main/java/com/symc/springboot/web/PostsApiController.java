package com.symc.springboot.web;

import com.symc.springboot.service.PostsService;
import com.symc.springboot.web.dto.PostsResponseDto;
import com.symc.springboot.web.dto.PostsSaveRequestDto;
import com.symc.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService ;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto) ;
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto) ;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto select(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id ;
    }
}
