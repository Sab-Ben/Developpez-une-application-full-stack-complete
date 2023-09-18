package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.models.Comments;
import com.openclassrooms.mddapi.models.Users;
import com.openclassrooms.mddapi.repository.CommentsRepository;
import com.openclassrooms.mddapi.security.service.UsersDetailsService;
import org.springframework.stereotype.Service;

/**
 * The type Comments service implements.
 *
 *  @author Sabrina BENSEGHIR
 *  @version 1.0
 *  @since   01-09-2023
 */
@Service
public class CommentsServiceImpl implements CommentsService{

    /**
     * The Comments Repository.
     */
    private final CommentsRepository commentsRepository;


    /**
     * The Users Detail Service.
     */
    private final UsersDetailsService usersDetailsService;

    /**
     * Instantiates a new Comment Service.
     *
     * @param commentsRepository the comments repository
     * @param usersDetailsService the user details service
     */
    public CommentsServiceImpl(CommentsRepository commentsRepository, UsersDetailsService usersDetailsService) {
        this.commentsRepository = commentsRepository;
        this.usersDetailsService = usersDetailsService;
    }

    /**
     * Creates a new comment authored by the currently authenticated user.
     *
     * @param comment The comment object containing the content and details of the new comment.
     * @return The newly created comment.
     */
    @Override
    public Comments create(Comments comment) {
        Users user = this.usersDetailsService.getUser();
        comment.setAuthor(user);
        return this.commentsRepository.save(comment);
    }

    /**
     * Retrieves the comments associated with a specific post by its ID.
     *
     * @param id The ID of the post for which comments will be retrieved.
     * @return An iterable collection of comments associated with the specified post.
     */
    @Override
    public Iterable<Comments> findPostsComments(Long id) {
        return this.commentsRepository.findByPostsId(id);
    }
}
