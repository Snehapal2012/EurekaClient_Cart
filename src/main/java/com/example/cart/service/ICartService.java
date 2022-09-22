package com.example.cart.service;

import com.example.cart.dto.CartDTO;
import com.example.cart.model.Cart;

import java.util.List;
import java.util.Optional;

public interface ICartService {
    List<Cart> getAll();

    Optional<Cart> getById(long id);
    Optional<Cart> getByUserId(long userId);

    void deleteById(long id);


    Cart UpdateQuantity(CartDTO cartDTO, long id);



    Cart updateById(CartDTO cartDTO, long cartId);

    Cart insert(CartDTO cartDTO);
}
