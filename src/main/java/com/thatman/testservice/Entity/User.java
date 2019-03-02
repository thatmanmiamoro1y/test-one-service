package com.thatman.testservice.Entity;

import com.thatman.testservice.config.ConfigValue;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class User {

    private String userName;

    private String userPassword;

    public User(){}

    public User(ConfigValue configValue){
        this.userName=configValue.getUserName();
        this.userPassword=configValue.getUserPassword();
    }
}
