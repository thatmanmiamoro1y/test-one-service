package com.thatman.testservice.service;


import com.thatman.testservice.Entity.User;
import org.springframework.stereotype.Service;

@Service
public interface TestService {
    User getUser();
}
