package com.thatman.testservice.config;

import com.thatman.testservice.Entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ConfigValue {
    @Value(value = "${userName:localhost-Name}")
    private String userName;
    @Value(value = "${userPassword:localhost-userPassword}")
    private String userPassword;

    @Bean
    public User user(){
        User user=new User();
        user.setUserName(this.userName);
        user.setUserPassword(this.userPassword);
        return user;
    }
}
