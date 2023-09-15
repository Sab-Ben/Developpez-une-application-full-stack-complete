package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.models.Comments;
import com.openclassrooms.mddapi.models.Users;
import com.openclassrooms.mddapi.repository.CommentsRepository;
import com.openclassrooms.mddapi.security.service.UsersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Comments service implements.
 */
@Service
public class CommentsServiceImpl implements CommentsService{

    private final CommentsRepository commentsRepository;

    private final UsersDetailsService usersDetailsService;

    public CommentsServiceImpl(CommentsRepository commentsRepository, UsersDetailsService usersDetailsService) {
        this.commentsRepository = commentsRepository;
        this.usersDetailsService = usersDetailsService;
    }


    @Override
    public Comments create(Comments comment) {
        Users user = this.usersDetailsService.getUser();
        comment.setAuthor(user);
        return this.commentsRepository.save(comment);
    }

    @Override
    public Iterable<Comments> findPostsComments(Long id) {
        return this.commentsRepository.findByPostsId(id);
    }
}
