package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.models.Topics;
/**
 * The interface Topics service.
 */
public interface TopicsService {
    Iterable<Topics> findAll();
    Topics findOne(Long id);
    Iterable<Topics> findUserSubscribedTopics();
    void subscribe(long topicsId);
    void unsubscribe(long topicsId);
}
