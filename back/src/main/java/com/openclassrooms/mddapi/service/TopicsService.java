package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.models.Topics;

public interface TopicsService {
    Iterable<Topics> findAll();
    Topics findOne(Long id);
    Iterable<Topics> findUserSubscribedTopics();
    void subscribe(long topicsId);
    void unsubscribe(long topicsId);
}
