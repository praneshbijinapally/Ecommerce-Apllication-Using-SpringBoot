package com.app.ecom_application.service;

import com.app.ecom_application.model.Address;
import com.app.ecom_application.dto.AdderssDto;
import com.app.ecom_application.dto.UserRequest;
import com.app.ecom_application.dto.UserResponse;
import com.app.ecom_application.model.User;
import com.app.ecom_application.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class userService {
     private final UserRepository userRepository;
    //private List<user> UserList=new ArrayList<>();
    //private Long nextId=1L;

    public userService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> fetchAllUsers(){
//        List<user>userList=userRepository.findAll();
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public void addUser(UserRequest userRequest){
//        user.setId(nextId++);
//        UserList.add(user);
        User User=new User();
        updateUserFromRequest(User,userRequest);
        userRepository.save(User);

    }




    public Optional<UserResponse> fetchUser(Long id) {
//        return UserList.stream()
//                .filter(User -> User.getId().equals(id))
//                .findFirst();
           return userRepository.findById(id)
                   .map(this::mapToUserResponse);

    }
    public boolean updateUser(Long id,UserRequest updatedUserRequest){
        return userRepository.findById(id)
                .map(existingUser->{
                    updateUserFromRequest(existingUser,updatedUserRequest);
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }

    private void updateUserFromRequest(User user, UserRequest userRequest) {
    user.setFirstName(userRequest.getFirstName());
    user.setLastName(userRequest.getLastName());
    user.setEmail(userRequest.getEmail());
    user.setPhone(userRequest.getPhone());


    if(userRequest.getAddress()!=null){
        Address address=new Address();
        address.setStreet(userRequest.getAddress().getStreet());
        address.setState(userRequest.getAddress().getState());
        address.setCountry(userRequest.getAddress().getCountry());
        address.setZipcode(userRequest.getAddress().getZipcode());
        address.setCity(userRequest.getAddress().getCity());
        user.setAddress(address);
    }
    }

    private UserResponse mapToUserResponse(User User){
      UserResponse response=new UserResponse();
      response.setId(String.valueOf(User.getId()));
      response.setFirstName(User.getFirstName());
      response.setLastName(User.getLastName());
      response.setEmail(User.getEmail());
      response.setPhone(User.getPhone());
      response.setRole(User.getRole());


      if(User.getAddress()!=null){
          AdderssDto adderssDto=new AdderssDto();
          adderssDto.setStreet(User.getAddress().getStreet());
          adderssDto.setCity(User.getAddress().getCity());
          adderssDto.setState(User.getAddress().getState());
          adderssDto.setCountry(User.getAddress().getCountry());
          adderssDto.setZipcode(User.getAddress().getZipcode());
          response.setAddress(adderssDto);

      }
      return response;
    }
}
