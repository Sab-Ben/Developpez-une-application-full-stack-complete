package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TopicsDto;
import com.openclassrooms.mddapi.models.Topics;
import com.openclassrooms.mddapi.service.TopicsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * The type Topics controller.
 *
 *  @author Sabrina BENSEGHIR
 *  @version 1.0
 *  @since   01-09-2023
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/topics")
public class TopicsController implements SecurityController {
    /**
     * The Topics service.
     */
    private final TopicsService topicsService;

    /**
     * The Model mapper.
     */
    private final ModelMapper modelMapper;
    /**
     * Instantiates a new Topic controller.
     *
     * @param topicsService  the topic service
     * @param modelMapper the model
     */
    public TopicsController(TopicsService topicsService, ModelMapper modelMapper) {
        this.topicsService = topicsService;
        this.modelMapper = modelMapper;
    }

    /**
     * Get all topics.
     *
     * @return A ResponseEntity with a collection of all topics in the form of DTOs (Data Transfer Objects)
     *         upon successful retrieval or an error message for unauthorized access.
     */
    @Operation(summary = "Get all topics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @GetMapping()
    public ResponseEntity<?> findAll() {
        Iterable<Topics> topics = this.topicsService.findAll();
        Iterable<TopicsDto> topicsDto = this.modelMapper.map(topics, new TypeToken<Iterable<TopicsDto>>() {}.getType());
        return ResponseEntity.ok(topicsDto);
    }


    /**
     * Get one topic by its ID.
     *
     * @param id The ID of the topic to retrieve.
     * @return A ResponseEntity with the topic information upon successful retrieval or an error message
     *         for unauthorized access.
     */
    @Operation(summary = "Get one topic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
        Topics topics = this.topicsService.findOne(id);
        return ResponseEntity.ok(topics);
    }


    /**
     * Get user's subscriptions.
     *
     * @return A ResponseEntity with a collection of topics the user is subscribed to in the form of DTOs
     *         (Data Transfer Objects) upon successful retrieval or an error message for unauthorized access.
     */
    @Operation(summary = "Get subscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @GetMapping("/mySubscriptions")
    public ResponseEntity<?> findUserSubscribedTopics(){
        Iterable<Topics> topics = this.topicsService.findUserSubscribedTopics();
        Iterable<TopicsDto> topicsDto = this.modelMapper.map(topics, new TypeToken<Iterable<TopicsDto>>() {}.getType());
        return ResponseEntity.ok(topicsDto);
    }

    /**
     * Subscribe to a topic.
     *
     * @param topicsId The ID of the topic to subscribe to.
     * @return A ResponseEntity to indicate successful subscription or a bad request response
     *         if the provided topic ID is invalid.
     */
    @Operation(summary = "Subscribe topic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @PostMapping("/{topics_id}/subscribe")
    public ResponseEntity<?> subscribe(@PathVariable("topics_id") Long topicsId) {
        try {
            this.topicsService.subscribe(topicsId);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException exception){
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Unsubscribe from a topic.
     *
     * @param topicsId The ID of the topic to unsubscribe from.
     * @return A ResponseEntity to indicate successful unsubscription or a bad request response
     *         if the provided topic ID is invalid.
     */
    @Operation(summary = "Unsubscribe topic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @DeleteMapping("/{topics_id}/unsubscribe")
    public ResponseEntity<?> unsubscribe(@PathVariable("topics_id") Long topicsId) {
        try {
            this.topicsService.unsubscribe(topicsId);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
