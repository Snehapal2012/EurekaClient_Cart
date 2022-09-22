package com.example.cart.service;

import com.example.cart.exception.CartException;
import com.example.cart.model.Book;
import com.example.cart.model.User;
import com.example.cart.repository.CartRepo;
import com.example.cart.dto.CartDTO;
import com.example.cart.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService{
    //Create dependency injection for CartRepo class
    @Autowired
    CartRepo cartRepo;
    //Apply logic for Insert cart details in the database
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public Cart insert(CartDTO cartDTO) {
        User user = restTemplate.getForObject("http://localhost:8081/user/id/"+cartDTO.getUserId(), User.class);
        Book book= restTemplate.getForObject("http://localhost:8082/book/id/"+cartDTO.getBookId(), Book.class);
        System.out.println(user.getFirstName());
        System.out.println(book.getBookName());
        if (user.equals(null) || book.equals(null)) {
            throw new CartException("User or Book Id is invalid! please check and try again!");
        } else {
            Cart cart = new Cart(cartDTO);
            cartRepo.save(cart);
            return cart;
        }
    }
    //Apply logic for Getting all cart details present in the database
    @Override
    public List<Cart> getAll(){
        List<Cart> cartList=cartRepo.findAll();
        return cartList;
    }
    //Apply logic for Getting particular cart details which will be found by id
    @Override
    public Optional<Cart> getById(long id){
        Optional<Cart> cart=cartRepo.findById(id);
        if (cart.isPresent()){
            return cart;
        }else {
            throw new CartException("Sorry! We can not find the cart id: "+id);
        }
    }
    //Apply logic for Getting particular cart details which will be found by user id
    @Override
    public Optional<Cart> getByUserId(long userId) {
            Optional<Cart> cart = cartRepo.findByUserId(userId);
            if (cart.isPresent()) {
                return cart;
            } else {
                throw new CartException("Sorry! We can not find the cart user id: " + userId);
            }
    }

    //Apply logic for Deleting particular cart details which will be found by id
    @Override
    public void deleteById(long id){
        Optional<Cart> cart=cartRepo.findById(id);
        if (cart.isPresent()){
            cartRepo.deleteById(id);
        }else {
            throw new CartException("Sorry! We can not find cart id: "+id);
        }
    }
    //Apply logic for Updating particular cart details which will be found by id
    @Override
    public Cart updateById(CartDTO cartDTO, long cartId) {
        Cart cart = cartRepo.findById(cartId).get();
        User user = restTemplate.getForObject("http://localhost:8081/user/id/"+cartDTO.getUserId(), User.class);
        Book book = restTemplate.getForObject("http://localhost:8082/book/id/"+cartDTO.getBookId(), Book.class);
        System.out.println(user.getFirstName());
        System.out.println(book.getBookName());
        if (cartRepo.findById(cartId).isPresent()) {
            if (user.equals(null) || book.equals(null)) {
                cart.setUserId(cartDTO.getUserId());
                cart.setBookId(cartDTO.getBookId());
                cart.setQuantity(cartDTO.getQuantity());
                cartRepo.save(cart);
                return cart;
            }else{
                throw new CartException("User or Book is not present in the database!");
            }
        } else {
            throw new CartException("Sorry! Your details are incorrect! Please check!");
        }
    }
    //Apply logic for Updating quantity for particular cart which will be found by id
    @Override
    public Cart UpdateQuantity(CartDTO cartDTO, long id){
        Cart cart=cartRepo.findById(id).get();
        if(cartRepo.findById(id).isPresent()){
            cart.setQuantity(cartDTO.getQuantity());
            cartRepo.save(cart);
            return cart;
        }else {
            throw new CartException("Sorry! We can not find cart id: "+id);
        }
    }
}
