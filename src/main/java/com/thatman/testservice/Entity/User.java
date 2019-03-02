package com.thatman.testservice.Entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    public @Value(value = "${userName}") String userName;
    public @Value(value = "${userPassword}")  String userPassword;
}
