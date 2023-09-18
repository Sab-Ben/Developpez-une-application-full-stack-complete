package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentsDto;
import com.openclassrooms.mddapi.dto.PostsDto;
import com.openclassrooms.mddapi.mapper.CommentsMapper;
import com.openclassrooms.mddapi.mapper.PostsMapper;
import com.openclassrooms.mddapi.models.Comments;
import com.openclassrooms.mddapi.models.Posts;
import com.openclassrooms.mddapi.service.CommentsService;
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
 *
 *  @author Sabrina BENSEGHIR
 *  @version 1.0
 *  @since   01-09-2023
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/posts")
public class PostsController implements SecurityController{
    /**
     * The Posts service.
     */
    private final PostsService postsService;

    /**
     * The Posts mapper.
     */
    private final PostsMapper postsMapper;

    /**
     * The Comments service.
     */
    private final CommentsService commentsService;

    /**
     * The Comments mapper.
     */
    private final CommentsMapper commentsMapper;

    /**
     * Instantiates a new Post controller.
     *
     * @param postsService  the posts service
     * @param postsMapper the posts mapper
     * @param commentsService the comments service
     * @param commentsMapper the comments mapper
     */
    public PostsController(PostsService postsService, PostsMapper postsMapper, CommentsService commentsService, CommentsMapper commentsMapper) {
        this.postsService = postsService;
        this.postsMapper = postsMapper;
        this.commentsService = commentsService;
        this.commentsMapper = commentsMapper;
    }

    /**
     * Get all posts.
     *
     * @return A ResponseEntity with a collection of posts in the form of DTOs (Data Transfer Objects)
     *         upon successful retrieval or an error message for unauthorized access.
     */
    @Operation(summary = "Get all posts")
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

    /**
     * Get post by ID.
     *
     * @param id The ID of the post to retrieve.
     * @return A ResponseEntity with the post information in the form of a DTO (Data Transfer Object)
     *         upon successful retrieval, a not found response if the post does not exist, or
     *         a bad request response if the provided ID is invalid.
     */
    @Operation(summary = "Get post")
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


    /**
     * Create a new post.
     *
     * @param postsDto The post data transfer object (DTO) containing the content and details of the new post.
     * @return A ResponseEntity with the newly created post in the form of a DTO upon
     *         successful creation or an error message for unauthorized access.
     */
    @Operation(summary = "Create post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody PostsDto postsDto){
        Posts post = this.postsService.create(this.postsMapper.map(postsDto, Posts.class));
        return ResponseEntity.ok(this.postsMapper.map(post, PostsDto.class));
    }

    /**
     * Get comments for a post by its ID.
     *
     * @param id The ID of the post for which comments will be retrieved.
     * @return A ResponseEntity with a collection of comments in the form of DTOs (Data Transfer Objects)
     *         upon successful retrieval or an error message for unauthorized access.
     */
    @Operation(summary = "Get comments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @GetMapping("/{id}/comments")
    public ResponseEntity<?> findPostsComments(@PathVariable("id") Long id) {
        Iterable<Comments> comments = this.commentsService.findPostsComments(id);
        Iterable<CommentsDto> formattedComments = this.commentsMapper.mapToDtoList(comments);
        return ResponseEntity.ok(formattedComments);
    }
}
