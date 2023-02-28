package com.example.claimsmicroservice.usermcroservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="AuthentificationMicroService", url = "http://localhost:8093/user")
public interface UserDtoClient {

    @GetMapping("/listUser")
    public List<UserDto> listUsers();
}
