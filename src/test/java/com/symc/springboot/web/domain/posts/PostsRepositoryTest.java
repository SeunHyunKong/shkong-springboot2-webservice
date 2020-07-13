package com.symc.springboot.web.domain.posts;

import com.symc.springboot.domain.posts.Posts;
import com.symc.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository ;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void board_list() {
        String title = "테스트 게시글";
        String contents = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .contents(contents)
                .author("seunghyun.kong@smotor.com")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title) ;
        assertThat(posts.getContents()).isEqualTo(contents) ;
    }

    @Test
    public void BaseTimeEntity_regist() {
        //given

        LocalDateTime now = LocalDateTime.of(2020, 7, 7, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .contents("contents")
                .author("seunghyun.kong@smotor.com")
                .build()) ;

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println("create date : " + posts.getCreatedDate() + "modifited date : " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
