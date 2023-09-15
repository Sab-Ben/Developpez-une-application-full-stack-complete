package com.openclassrooms.mddapi.service;
/**
 * The interface Posts service.
 */

import com.openclassrooms.mddapi.models.Posts;

public interface PostsService {
    Iterable<Posts> findFeedPosts();
    Posts findOne(long id);
    Posts create(Posts post);
}
