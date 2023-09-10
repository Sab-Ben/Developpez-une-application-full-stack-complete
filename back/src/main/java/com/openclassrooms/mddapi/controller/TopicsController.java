package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TopicsDto;
import com.openclassrooms.mddapi.models.Topics;
import com.openclassrooms.mddapi.service.TopicsServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/topics")
public class TopicsController {

    private final TopicsServiceImpl topicsService;

    private final ModelMapper modelMapper;

    public TopicsController(TopicsServiceImpl topicsService, ModelMapper modelMapper) {
        this.topicsService = topicsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        Iterable<Topics> topics = this.topicsService.findAll();
        Iterable<TopicsDto> topicsDto = this.modelMapper.map(topics, new TypeToken<Iterable<TopicsDto>>() {}.getType());
        return ResponseEntity.ok(topicsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
        Topics topics = this.topicsService.findOne(id);
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/mySubscriptions")
    public ResponseEntity<?> findUserSubscribedTopics(){
        Iterable<Topics> topics = this.topicsService.findUserSubscribedTopics();
        Iterable<TopicsDto> topicsDto = this.modelMapper.map(topics, new TypeToken<Iterable<TopicsDto>>() {}.getType());
        return ResponseEntity.ok(topicsDto);
    }

    @PostMapping("/{topics_id}/subscribe")
    public ResponseEntity<?> subscribe(@PathVariable("topics_id") Long topicsId) {
        try {
            this.topicsService.subscribe(topicsId);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException exception){
            return ResponseEntity.badRequest().build();
        }
    }

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
