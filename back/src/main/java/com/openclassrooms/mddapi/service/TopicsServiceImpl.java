package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.models.Topics;
import com.openclassrooms.mddapi.models.Users;
import com.openclassrooms.mddapi.repository.TopicsRepository;
import com.openclassrooms.mddapi.security.service.UsersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Service
public class TopicsServiceImpl implements TopicsService{

    private final TopicsRepository topicsRepository;

    private final UsersDetailsService usersDetailsService;

    public TopicsServiceImpl(TopicsRepository topicsRepository, UsersDetailsService usersDetailsService) {
        this.topicsRepository = topicsRepository;
        this.usersDetailsService = usersDetailsService;
    }

    @Override
    public Iterable<Topics> findAll() {
        return this.topicsRepository.findAllByOrderByTitleAsc();
    }

    @Override
    public Topics findOne(Long id) {
        return this.topicsRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Topics> findUserSubscribedTopics() {
        Users users = this.usersDetailsService.getUser();
        return this.topicsRepository.findSubscribedTopicsByUsersId(users.getId());
    }

    @Override
    public void subscribe(long topicsId) {
        Topics topics = this.topicsRepository.findById(topicsId).orElseThrow(NotFoundException::new);
        if (this.isUserSubscribed(topics)) {
            return;
        }
        topics.getUsers().add(this.usersDetailsService.getUser());
        this.topicsRepository.save(topics);
    }

    @Override
    public void unsubscribe(long topicsId) {
        Topics topics = this.topicsRepository.findById(topicsId).orElseThrow(NotFoundException::new);

        if (this.isUserSubscribed(topics)) {
            topics.setUsers(this.removeUserFromSubscriptionList(topics));
            this.topicsRepository.save(topics);
        }
    }

    private boolean isUserSubscribed(Topics topics){
        Users user = this.usersDetailsService.getUser();
        return topics.getUsers().stream().anyMatch(u -> Objects.equals(u.getId(), user.getId()));
    }

    private List<Users> removeUserFromSubscriptionList(Topics topics) {
        Users authenticatedUser = this.usersDetailsService.getUser();
        List<Users> userss = topics.getUsers();
        return userss.stream().filter(users -> !Objects.equals(users.getId(), authenticatedUser.getId())).collect(Collectors.toList());
    }
}
