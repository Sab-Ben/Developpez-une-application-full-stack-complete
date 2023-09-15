package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.Instant;

/**
 * The type Topics Dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicsDto {
    Long id;

    @NonNull
    String title;

    @NonNull
    String description;

    private Instant created_at;

    private Instant updated_at;
}
