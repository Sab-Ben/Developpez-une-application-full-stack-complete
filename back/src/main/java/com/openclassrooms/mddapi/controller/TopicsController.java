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
