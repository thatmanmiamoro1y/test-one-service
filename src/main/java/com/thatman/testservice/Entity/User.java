package com.thatman.testservice.Entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class User {
    @Value(value = "${userName:localhost-Name}")
    private String userName;
    @Value(value = "${userPassword:localhost-userPassword}")
    private String userPassword;

}
