package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentsDto;
import com.openclassrooms.mddapi.mapper.CommentsMapper;
import com.openclassrooms.mddapi.models.Comments;
import com.openclassrooms.mddapi.service.CommentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Comments controller.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/comment")
public class CommentsController implements SecurityController{

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
     * @param commentsService the comments service
     * @param commentsMapper the comments mapper
     */

    public CommentsController(CommentsService commentsService, CommentsMapper commentsMapper) {
        this.commentsService = commentsService;
        this.commentsMapper = commentsMapper;
    }

    @Operation(summary = "Get comments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<?> findPostsComments(@PathVariable("id") String id) {
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Create comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody CommentsDto commentsDto) {
        Comments comment = this.commentsService.create(this.commentsMapper.map(commentsDto, Comments.class));

        return ResponseEntity.ok(this.commentsMapper.map(comment, CommentsDto.class));
    }
}
