package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * The interface Comments repository.
 */
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    Iterable<Comments> findByPostsId(@Param("postsId") Long postsId);
}
