package com.example.cart.model;

import com.example.cart.dto.CartDTO;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Data
@Table(name = "cart")
@NoArgsConstructor
public class Cart {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartId;

    private long userId;

    private long bookId;
    private long quantity;

    public Cart(CartDTO cartDTO) {
        this.bookId=cartDTO.getBookId();
        this.userId=cartDTO.getUserId();
        this.quantity=cartDTO.getQuantity();
    }
}
