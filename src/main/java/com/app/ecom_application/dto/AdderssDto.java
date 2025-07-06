package com.app.ecom_application.dto;

import com.app.ecom_application.model.UserRole;
import lombok.Data;

@Data
public class AdderssDto {
    //private String id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
