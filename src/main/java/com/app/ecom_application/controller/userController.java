package com.app.ecom_application.controller;

import com.app.ecom_application.dto.UserRequest;
import com.app.ecom_application.dto.UserResponse;
import com.app.ecom_application.service.userService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/user")
public class userController {

        private final userService UserService;

    public userController(userService userService) {
        UserService = userService;
    }


    @GetMapping
        public List<UserResponse >getAllUsers(){
           return UserService.fetchAllUsers();
       }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        return UserService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());


    }
       @PostMapping
        public String createUser(@RequestBody UserRequest userRequest){
           UserService.addUser(userRequest);
           return "User Added Successfully";
       }
       @PutMapping("/{id}")
    public ResponseEntity<String>updateUser(@PathVariable Long id,
                                            @RequestBody UserRequest updatedUserRequest){
        boolean updated=UserService.updateUser(id,updatedUserRequest);
        if(updated)
            return ResponseEntity.ok("user updated succesfully");

        return ResponseEntity.notFound().build();

       }
    }

