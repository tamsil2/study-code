package me.kobeshow.springbootjpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void findByTitleStartsWith() {
        Post post = new Post();
        post.setTitle("Spring Data Jpa");
        postRepository.save(post);

        List<Post> all = postRepository.findByTitleStartsWith("Spring");
        assertEquals(1, all.size());
    }

    @Test
    public void findByTitle() {
        Post post = new Post();
        post.setTitle("Spring");
        postRepository.save(post);

        List<Post> all = postRepository.findByTitle("Spring", Sort.by("title"));
        assertEquals(1, all.size());
    }

    @Test
    public void updateTitle() {
        Post post = new Post();
        post.setTitle("Spring");
        Post spring = postRepository.save(post);
        spring.setTitle("hibernate");

        List<Post> all = postRepository.findAll();
        assertEquals("hibernate", all.get(0).getTitle());
    }
}
