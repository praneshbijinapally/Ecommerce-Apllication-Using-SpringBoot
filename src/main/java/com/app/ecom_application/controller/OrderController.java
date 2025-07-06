package com.app.ecom_application.controller;

import com.app.ecom_application.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
//@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
   @Autowired
    public OrderController(OrderService orderService) {
       this.orderService = orderService;
    }
    @PostMapping
    public ResponseEntity<OrderResponse>createResponse(
            @RequestHeader("X-User-ID")String userId){
        return orderService.createOrder(userId)
                .map(orderResponse -> new ResponseEntity<>(orderResponse,HttpStatus.CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());


    }

}
