package com.app.ecom_application.controller;

import com.app.ecom_application.dto.CartItemRequest;
import com.app.ecom_application.model.CartItem;
import com.app.ecom_application.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
//@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<String>addToCart(
            @RequestHeader("X-User-ID")String userId,
            @RequestBody CartItemRequest request){
        if(!cartService.addToCart(userId,request)){
            return ResponseEntity.badRequest().body("Product Out Of Stock or User Not Found or Product Not Found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void>removeFromCart(
     @RequestHeader("X-User-ID") String userId,
     @PathVariable Long productId
    ){
       Boolean deleted = cartService.deleteItemFromCart(userId,productId);
       return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping
        public ResponseEntity<List<CartItem>> getCart(
                @RequestHeader("X-User-ID") String userId){
            return ResponseEntity.ok(cartService.getCart(userId));
        }

}






