package com.app.ecom_application.dto;

import com.app.ecom_application.model.UserRole;
import lombok.Data;

@Data
public class UserResponse {

    private String id;
    private String FirstName;
    private String LastName;
    private String email;
    private String phone;
    private UserRole role;
    private AdderssDto address;
}
