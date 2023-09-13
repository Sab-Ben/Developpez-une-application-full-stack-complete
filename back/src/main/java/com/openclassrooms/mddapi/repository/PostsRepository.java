package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface Posts repository.
 */
@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

    Iterable<Posts> findAllByOrderByIdDesc();

    @Query(value = "SELECT p FROM Posts p JOIN p.topics t JOIN t.users u WHERE u.id = :usersId ORDER BY t.id ASC")
    Iterable<Posts> findUserFeed(@Param("usersId") Long usersId);
}
