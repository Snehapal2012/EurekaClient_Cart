package com.example.cart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CartDTO {
    @NotNull(message = "User Id can not be null!")
    private long userId;
    @NotNull(message = "Book Id can not be null!")
    private long bookId;
    @NotNull(message = "Quantity can not be null!")
    private long quantity;
}
