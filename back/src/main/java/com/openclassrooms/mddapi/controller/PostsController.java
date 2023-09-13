package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.PostsDto;
import com.openclassrooms.mddapi.mapper.PostsMapper;
import com.openclassrooms.mddapi.models.Posts;
import com.openclassrooms.mddapi.service.PostsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Posts controller.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/posts")
public class PostsController {
    /**
     * The Posts service.
     */
    private final PostsService postsService;

    /**
     * The Posts mapper.
     */

    private final PostsMapper postsMapper;

    /**
     * Instantiates a new Topic controller.
     *
     * @param postsService  the posts service
     * @param postsMapper the posts mapper
     */

    public PostsController(PostsService postsService, PostsMapper postsMapper) {
        this.postsService = postsService;
        this.postsMapper = postsMapper;
    }

    @Operation(summary = "Get all topics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @GetMapping("/feed")
    public ResponseEntity<?> findFeedPosts(){
        Iterable<Posts> posts = this.postsService.findFeedPosts();
        Iterable<PostsDto> postsResponse = this.postsMapper.mapToDtoList(posts);
        return ResponseEntity.ok(postsResponse);
    }


    @Operation(summary = "Get topic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") final long id){
        try {
            Posts post = postsService.findOne((id));
            if (post == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(this.postsMapper.map(post, PostsDto.class));
        } catch (NumberFormatException e){
            return ResponseEntity.badRequest().build();
        }
    }


    @Operation(summary = "Create topic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody PostsDto postsDto){
        Posts post = this.postsService.create(this.postsMapper.map(postsDto, Posts.class));
        return ResponseEntity.ok(this.postsMapper.map(post, PostsDto.class));
    }
}
