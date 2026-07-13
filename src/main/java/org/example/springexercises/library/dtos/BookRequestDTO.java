package org.example.springexercises.library.dtos;

import jakarta.validation.constraints.NotBlank;

public record BookRequestDTO (
        @NotBlank(message = "Title cannot be blank")
        String title,

        @NotBlank(message = "Title cannot be blank")
        String author,

        Integer publicationYear
) {
}
