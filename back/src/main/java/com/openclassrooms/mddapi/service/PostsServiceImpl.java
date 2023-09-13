package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.models.Posts;
import com.openclassrooms.mddapi.models.Users;
import com.openclassrooms.mddapi.repository.PostsRepository;
import com.openclassrooms.mddapi.security.service.UsersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Posts service implements.
 */

@Service
public class PostsServiceImpl implements PostsService{
    private final PostsRepository postsRepository;

    private final UsersDetailsService usersDetailsService;

    public PostsServiceImpl(PostsRepository postsRepository, UsersDetailsService usersDetailsService) {
        this.postsRepository = postsRepository;
        this.usersDetailsService = usersDetailsService;
    }

    @Override
    public Iterable<Posts> findFeedPosts() {
        Users user = this.usersDetailsService.getUser();
        return this.postsRepository.findUserFeed(user.getId());
    }

    @Override
    public Posts findOne(long id) {
        return this.postsRepository.findById((id)).orElseThrow(NotFoundException::new);
    }

    @Override
    public Posts create(Posts post) {
        Users user = this.usersDetailsService.getUser();
        post.setAuthor(user);

        return this.postsRepository.save(post);
    }
}
