package me.tamsil.chapter01.item07.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostRepositoryTest {

    @Test
    void cache() throws InterruptedException{
        PostRepository postRepository = new PostRepository();
        CacheKey key1 = new CacheKey(1);
        postRepository.getPostById(key1);

        assertFalse(postRepository.getCache().isEmpty());

        // TODO run gc
        System.out.println("run gc");
        System.gc();
        System.out.println("wait");
        Thread.sleep(3000L);

        assertTrue(postRepository.getCache().isEmpty());
    }

    @Test
    void backgroundThread() throws InterruptedException {

    }
}
