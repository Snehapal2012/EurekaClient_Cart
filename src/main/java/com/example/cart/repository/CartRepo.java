package com.example.cart.repository;

import com.example.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
    @Query(value = "select * from book_store_cart.cart where cart.user_id=:userId",nativeQuery = true)
    Optional<Cart> findByUserId(long userId);
    @Transactional
    @Modifying
    @Query(value = "delete from book_store_cart.cart where cart.cart_id=:id",nativeQuery = true)
    void deleteById(long id);
}
