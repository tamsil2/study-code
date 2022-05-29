package me.tamsil.chapter01.item07.cache;

import java.util.HashMap;
import java.util.Map;

public class PostRepository {
    private Map<CacheKey, Post> cache;

    public PostRepository() {
    }

    public PostRepository(Map<CacheKey, Post> cache) {
        this.cache = new HashMap<>();
    }

    public Post getPostById(CacheKey key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        } else {
            // TODO DB에서 읽어오거나 REST API를 통해 읽어올 수 있습니다.
            Post post = new Post();
            cache.put(key, post);
            return post;
        }
    }

    public Map<CacheKey, Post> getCache() {
        return cache;
    }
}
