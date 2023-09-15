package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.models.Comments;
/**
 * The interface Comments service.
 */
public interface CommentsService {

    Comments create(Comments comment);
    Iterable<Comments> findPostsComments(Long id);
}
