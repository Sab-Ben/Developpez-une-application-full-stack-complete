package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.models.Topics;
import com.openclassrooms.mddapi.models.Users;
import com.openclassrooms.mddapi.repository.TopicsRepository;
import com.openclassrooms.mddapi.security.service.UsersDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
/**
 * The type Topics service implements.
 *
 *  @author Sabrina BENSEGHIR
 *  @version 1.0
 *  @since   01-09-2023
 */
@Service
public class TopicsServiceImpl implements TopicsService{

    /**
     * The Topics Repository.
     */
    private final TopicsRepository topicsRepository;

    /**
     * The User Detail Service.
     */
    private final UsersDetailsService usersDetailsService;

    /**
     * Instantiates a new Topic Service.
     *
     * @param topicsRepository the topics repository
     * @param usersDetailsService the user details service
     */

    public TopicsServiceImpl(TopicsRepository topicsRepository, UsersDetailsService usersDetailsService) {
        this.topicsRepository = topicsRepository;
        this.usersDetailsService = usersDetailsService;
    }

    /**
     * Retrieves all topics from the repository in ascending order of their titles.
     *
     * @return An iterable collection of topics sorted by title in ascending order.
     */
    @Override
    public Iterable<Topics> findAll() {
        return this.topicsRepository.findAllByOrderByTitleAsc();
    }


    /**
     * Retrieves a topic with the specified ID from the repository.
     *
     * @param id The ID of the topic to retrieve.
     * @return The topic with the specified ID, or null if not found.
     */
    @Override
    public Topics findOne(Long id) {
        return this.topicsRepository.findById(id).orElse(null);
    }


    /**
     * Retrieves the topics to which the currently authenticated user is subscribed.
     *
     * @return An iterable collection of topics that the authenticated user is subscribed to.
     */
    @Override
    public Iterable<Topics> findUserSubscribedTopics() {
        Users users = this.usersDetailsService.getUser();
        return this.topicsRepository.findSubscribedTopicsByUsersId(users.getId());
    }

    /**
     * Subscribes the currently authenticated user to a specified topic by its ID.
     *
     * @param topicsId The ID of the topic to which the user will be subscribed.
     * @throws NotFoundException If the specified topic ID does not exist in the repository.
     */
    @Override
    public void subscribe(long topicsId) {
        Topics topics = this.topicsRepository.findById(topicsId).orElseThrow(NotFoundException::new);
        if (this.isUserSubscribed(topics)) {
            return;
        }
        topics.getUsers().add(this.usersDetailsService.getUser());
        this.topicsRepository.save(topics);
    }

    /**
     * Unsubscribes the currently authenticated user from a specified topic by its ID.
     *
     * @param topicsId The ID of the topic from which the user will be unsubscribed.
     * @throws NotFoundException If the specified topic ID does not exist in the repository.
     */
    @Override
    public void unsubscribe(long topicsId) {
        Topics topics = this.topicsRepository.findById(topicsId).orElseThrow(NotFoundException::new);

        if (this.isUserSubscribed(topics)) {
            topics.setUsers(this.removeUserFromSubscriptionList(topics));
            this.topicsRepository.save(topics);
        }
    }

    /**
     * Checks if the currently authenticated user is subscribed to a specified topic.
     *
     * @param topics The topic to check for user subscription.
     * @return {@code true} if the user is subscribed to the topic, {@code false} otherwise.
     */
    private boolean isUserSubscribed(Topics topics){
        Users user = this.usersDetailsService.getUser();
        return topics.getUsers().stream().anyMatch(u -> Objects.equals(u.getId(), user.getId()));
    }

    /**
     * Removes the currently authenticated user from the list of subscribers for a specified topic.
     *
     * @param topics The topic from which the user will be removed.
     * @return A list of users with the authenticated user removed from the subscription list.
     */
    private List<Users> removeUserFromSubscriptionList(Topics topics) {
        Users authenticatedUser = this.usersDetailsService.getUser();
        List<Users> userss = topics.getUsers();
        return userss.stream().filter(users -> !Objects.equals(users.getId(), authenticatedUser.getId())).collect(Collectors.toList());
    }
}
