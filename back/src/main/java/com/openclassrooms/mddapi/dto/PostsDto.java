package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * The type Posts Dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsDto {

    private long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Long topics;

    private String author;

    private Instant created_at;

    private Instant updated_at;
}
