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
 *
 *  @author Sabrina BENSEGHIR
 *  @version 1.0
 *  @since   01-09-2023
 */

@Service
public class PostsServiceImpl implements PostsService{
    /**
     * The Posts Repository.
     */
    private final PostsRepository postsRepository;

    /**
     * The User Detail Service.
     */
    private final UsersDetailsService usersDetailsService;


    /**
     * Instantiates a new Post Service.
     *
     * @param postsRepository the posts repository
     * @param usersDetailsService the user details service
     */
    public PostsServiceImpl(PostsRepository postsRepository, UsersDetailsService usersDetailsService) {
        this.postsRepository = postsRepository;
        this.usersDetailsService = usersDetailsService;
    }

    /**
     * Retrieves the feed of posts for the currently authenticated user.
     *
     * @return An iterable collection of posts in the user's feed.
     */
    @Override
    public Iterable<Posts> findFeedPosts() {
        Users user = this.usersDetailsService.getUser();
        return this.postsRepository.findUserFeed(user.getId());
    }


    /**
     * Retrieves a post with the specified ID from the repository.
     *
     * @param id The ID of the post to retrieve.
     * @return The post with the specified ID.
     * @throws NotFoundException If the specified post ID does not exist in the repository.
     */
    @Override
    public Posts findOne(long id) {
        return this.postsRepository.findById((id)).orElseThrow(NotFoundException::new);
    }


    /**
     * Creates a new post authored by the currently authenticated user.
     *
     * @param post The post object containing the content and details of the new post.
     * @return The newly created post.
     */
    @Override
    public Posts create(Posts post) {
        Users user = this.usersDetailsService.getUser();
        post.setAuthor(user);

        return this.postsRepository.save(post);
    }
}
