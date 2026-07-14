package org.example.springexercises.inventorycontrol.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;


public record ProductRequestDTO(
        @NotBlank(message = "Product name cannot be empty or null")
        String name,

        @NotNull(message = "Product price cannot be null")
        @Positive(message = "Product price must be positive")
        BigDecimal price,

        @NotNull(message = "Product inventory quantity cannot be null")
        @PositiveOrZero(message = "Product inventory quantity must be positive or zero")
        Integer quantityInInventory) {
}
