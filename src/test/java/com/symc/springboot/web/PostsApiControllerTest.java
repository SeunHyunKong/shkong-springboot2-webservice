package com.symc.springboot.web;

import com.symc.springboot.domain.posts.Posts;
import com.symc.springboot.domain.posts.PostsRepository;
import com.symc.springboot.web.dto.PostsSaveRequestDto;
import com.symc.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port ;

    @Autowired
    private TestRestTemplate restTemplate ;

    @Autowired
    private PostsRepository postsRepository ;

    @After
    public void tearDown() throws  Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void post_registration() throws Exception {
        //Given
        String title = "title" ;
        String contents = "content" ;

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .contents(contents)
                .author("seunghyun.kong@smotor.com")
                .build() ;

        String url = "http://localhost:" + port + "/api/v1/posts" ;

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class) ;

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK) ;
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(title) ;
        assertThat(all.get(0).getContents()).isEqualTo(contents) ;
    }

    @Test
    public void posts_update() throws Exception {
        Posts savePosts = postsRepository.save(Posts.builder()
                .title("title")
                .contents("contents")
                .author("seunghyun.kong@smotor.com")
                .build());

        Long updateId = savePosts.getId() ;
        String expectedTitle = "title2" ;
        String expectedContents = "contents2" ;

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .contents(expectedContents)
                .build() ;

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId ;
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContents()).isEqualTo(expectedContents);
    }
}
