package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Comments Dto.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {

    private long id;

    @NotBlank
    private String description;

    @NotNull
    private Long posts;

    private String author;

}
