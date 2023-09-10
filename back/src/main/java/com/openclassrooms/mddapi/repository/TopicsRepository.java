package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.models.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicsRepository extends JpaRepository<Topics, Long> {

    Iterable<Topics> findAllByOrderByTitleAsc();

    @Query("SELECT t FROM Topics t JOIN Subscriptions s ON t.id = s.topics_id WHERE s.users_id = :usersId ORDER BY t.title")
    Iterable<Topics> findSubscribedTopicsByUsersId(@Param("usersId") Long usersId);
}
