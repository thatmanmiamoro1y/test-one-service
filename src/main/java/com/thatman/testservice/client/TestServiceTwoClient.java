package com.thatman.testservice.client;


import com.thatman.testservice.Entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;


@Component
@FeignClient(name = "test-service-two",fallback = TestServiceTwoClientFallback.class)
public interface TestServiceTwoClient {

    @GetMapping(value ="test/getUser")
    User getUser();

}
